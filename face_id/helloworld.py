import requests

def fetch_api_result():
    # Replace with your API URL
    response = requests.get("x")
    if response.status_code == 200:
        print(response.text)
    else:
        print("Error fetching API data")

if __name__ == "__main__":
    fetch_api_result()
