import subprocess
import os

def start_tomcat():
    # Đường dẫn tới thư mục bin của Tomcat
    tomcat_path = r"C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\catalina.bat run"
    try:
        print("Starting Tomcat...")
        subprocess.Popen(tomcat_path, shell=True)
    except Exception as e:
        print(f"Failed to start Tomcat: {e}")

def start_python_script():
    # Đường dẫn tới file main.py
    python_script = r"c:\test\timeKeeping_ver1\face_id\main.py"
    try:
        print("Starting main.py...")
        subprocess.Popen(["python", python_script], shell=True)
    except Exception as e:
        print(f"Failed to start main.py: {e}")

if __name__ == "__main__":
    start_tomcat()
    start_python_script()
    print("Both Tomcat and main.py have been started.")
