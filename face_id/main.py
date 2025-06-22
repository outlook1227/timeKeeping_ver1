import cv2
import cv2.data
import tensorflow as tf

# Danh sách nhãn
labels = ['Ariel_Sharon', 'Colin_Powell', 'Donald_Rumsfeld', 'George_W_Bush', 'Gerhard_Schroeder',
          'Hugo_Chavez', 'Jacques_Chirac', 'Jean_Chretien', 'John_Ashcroft', 'Junichiro_Koizumi',
          'Serena_Williams', 'Tiendo2k1', 'Tony_Blair']

def draw_ped(img, label, x0, y0, xt, yt, color=(255, 127, 0), text_color=(255, 255, 255)):
    (w, h), baseline = cv2.getTextSize(label, cv2.FONT_HERSHEY_SIMPLEX, 0.5, 1)
    cv2.rectangle(img, (x0, y0 + baseline), (max(xt, x0 + w), yt), color, 2)
    cv2.rectangle(img, (x0, y0 - h), (x0 + w, y0 + baseline), color, -1)
    cv2.putText(img, label, (x0, y0), cv2.FONT_HERSHEY_SIMPLEX, 0.5, text_color, 1, cv2.LINE_AA)
    return img

# --------- load Haar Cascade model -------------
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

# --------- load Keras CNN model -------------
model = tf.keras.models.load_model("model.keras")
print("[INFO] finish load model...")

cap = cv2.VideoCapture(0)
MAX_FACES = 50  # Set the maximum total number of faces to process
total_faces_processed = 0  # Global counter for total faces processed

while cap.isOpened():
    ret, frame = cap.read()
    if ret:
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, 1.1, 5)
        face_count = 0  # Counter to track the number of processed faces in the current frame

        for (x, y, w, h) in faces:
            if total_faces_processed >= MAX_FACES:
                break  # Stop processing if the global limit is reached
            if face_count >= MAX_FACES:
                break  # Stop processing faces in the current frame

            face_img = gray[y:y+h, x:x+w]
            face_img = cv2.resize(face_img, (50, 50))
            face_img = face_img.reshape(1, 50, 50, 1)

            result = model.predict(face_img)
            idx = result.argmax(axis=1).item()  # Convert to scalar integer
            confidence = result.max(axis=1) * 100
            if confidence > 90:
                label_text = "%s (%.2f %%)" % (labels[idx], confidence)
            else:
                label_text = "N/A"
            frame = draw_ped(frame, label_text, x, y, x + w, y + h, color=(0, 255, 255), text_color=(50, 50, 50))

            face_count += 1  # Increment the counter for each processed face in the current frame
            total_faces_processed += 1  # Increment the global counter

        cv2.imshow('Detect Face', frame)

        # Check if 'q' is pressed to stop immediately
        if cv2.waitKey(10) & 0xFF == ord('s'):
            break
    else:
        break
    if total_faces_processed >= MAX_FACES or cv2.waitKey(10) == ord('q'):
        break  # Exit the loop if the global limit is reached or 'q' is pressed

cv2.destroyAllWindows()
cap.release()
