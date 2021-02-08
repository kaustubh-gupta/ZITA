# ZITA

## A step-by-step guide to run the project

Download the code from the repository and simply use Intellij with Maven to run the server and Android studio with gradle for the client and the Bluetooth App. The IDE should be able to automatically detect the project which can then be ran.

### Step 1. Installing the ZITA Bluetooth App
The ZITA Bluetooth App can be installed on two android phones to test the app. Once the app is downloaded, pair the two phones using bluetooth. When you open the app, you will see this screen:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210122-215956_ZITA%20Bluetooth.jpg" width="200" height="400" >
</p>

Next, make sure the app has permission to the phone storage in Settings. Then, click on the Bluetooth button on the top right corner and the list of available devices will pop up:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210122-220003_ZITA%20Bluetooth.jpg" width="200" height="400" >
</p>

Once a device is selected, the two phones will start generating the contact trace, like this:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210122-215930_ZITA%20Bluetooth.jpg" width="200" height="400" >
</p>

The contact traces are hashed and saved in both phones on quitting the application.

### Step 2. Installing the ZITA Client Server App
To run the server, simply run the application on Intellij: 

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/server/Screen%20Shot%202021-01-28%20at%206.54.06%20PM.png" width="710" height="400" >
</p>

Once the application has started, on the server application, click the "Run Server" button:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/server/Screen%20Shot%202021-01-28%20at%206.54.23%20PM.png" width="710" height="400" >
</p>

Then, through Android Studio, install the client app on two separate Android phones and make sure to give the application permission to access phone storage in Settings. When the app is opened, it should look like this:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190005_ZITA.jpg" width="200" height="400" >
</p>

#### (Note: One device is named D in the image, the other device will need to be named S and the IP address needs to be changed according to the IP Address of the machine running the server aplication)

Next, press the "Connect" button on the device named D first and then on the device named S. The two phones should then start connecting to the server and generating a key pair and exchanging them with the server as well:

<p align="center">
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190054_ZITA.jpg" width="200" height="400" >
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190059_ZITA.jpg" width="200" height="400" >
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190104_ZITA.jpg" width="200" height="400" >
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190111_ZITA.jpg" width="200" height="400" >
</p>

Once the key exchange is completed and both clients are connected to the server, the server application will populate the Session Key fields:

<p align="center">
<img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/server/Screen%20Shot%202021-01-28%20at%207.01.26%20PM.png" width="710" height="400" >
</p>

Now, the "Second-Factor AKA" button can be pressed to perform the Second Factor Authentication. Upon completion of the Second Factor Authentication, the server and the client phones will look like this:

<p align="center">
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/server/Screen%20Shot%202021-01-28%20at%207.02.13%20PM.png" width="710" height="400" >
</p>
<p align="center">
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneX/Screenshot_20210128-190204_ZITA.jpg" width="200" height="400" >
  <img src="https://github.com/kaustubh-gupta/ZITA/blob/main/ZITA_Screenshots/phoneY/Screenshot_20210128-190203_ZITA.jpg" width="200" height="400" >
</p>



