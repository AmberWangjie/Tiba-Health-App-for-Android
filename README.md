# README



Tiba Health is an Android mobile phone application which functions as a patient's care plan management tool. 
The app is able to work independently or paired with a wearable motion tracking device via bluetooth connection. 

  
## Feature Overview
The application will basically offer the following services:
  - Authenticate users to register or login to the account
  - Load the exercise plan according to the user's identification from remote database
  - Display the exercise sets and their description in an overall or a separate view
  - Count time for each exercise task and switch to the next one by a pre-set order
  - Send and receive exercise information between devices via bluetooth
  - Store information locally on the Android device
  - Redirect to the email app on device to support mail sending/cancelling


## Implementation Structure:
The implementation code for this application project were mostly structured as different fragments and also some activities, 
which depend on what functional component they act as in the overall architecture.

  - The UI component includes several fragments which basically will work with the users directly.   The specific java files 
belonging to this part are as following:
    1. AccountFragment.java
    2. CareplanFragment.java
    3. EmailFragment.java
    4. DescriptionActivity.java
    5. WorkoutFragment.java
    6. rateActivity.java
    7. LoginActivity.java
    8. TrainActivity.java
    

  - The device component is basically responsible for interacting with the wearable device to accomplish the data transmission 
job. The specific java files include:
     1. BluetoothActivity.java
     2. BluetoothConnectionServices.java
     3. DeviceListAdapter.java
     

  - The backend implementation is basically focused on the  network server and database server, which will be built connection
with the frontend components via HTTP request/response and then do the query operation. There is also a local storage component 
so that the items stored there will be fetched without conncting to the server. The code involved in this part are listed as following:
     1. SettingFragment.java
     2. LocalDBActivity.java
     3. SignupActivity.java



  - Besides the java files under /app/java/main, there are also three class files under /data, which are:
     1. Exercise.java
     2. Task.java
     3. User.java
    

  - All the UI design xml files are under /res/layout, whose attribution can be easily recognized by their name, which reflected on which 
functional components they served.


## Get Started
- First, to make the app UI display properly, make sure to run the code on an mobile device with Android operating system, either an emulator or real mobile phone. 
- This app is compatible with Android API 25 ideally since that was what we use when building the app. The app requires connection to Internet in order to connect to web server and remote database to save and retrieve data.
- Then, to login as the user, make sure to create a new account if you don't have one before and the username and password have to be unique and valid
- After sucessfully login, you should be play with the app, the first page to be displayed is the summary of the careplan with all the exercise tasks to do and some statistic information about them, the user can choose to hit the button at the bottom or navigate to another page by the bottom navigation bar
  1. If the "start workout" button is hit, all the exercises tasks for today should be done in a certain order(which was also part of the design of the care plan), first it will show the exercise name and its description;
  2. There shall be a "go" button on each description page, once hit, the user shall be able to start the workout by hitting the start button, then the timer shall start counting the time until the end of this exercise unit, then the app shall automatically switch to next exercise unit in the task list, and the process shall be repeated;
- After all the tasks for today being finished, the user will be able to choose the emoji button to rate for the working out experience;
- The third option in the bottom navigation bar is for email feature, by hitting the envolop-like button, the app shall be redirected to the email server installed on the android device (You may also choose the email server if there are more one available), note that this necessary to use this feature, in addtion, you need also to sign in using a valid, registered email address on the email server you are going to use; after sending the email, it shall be redirected back to the app;
- The data will be automatically saved into the local database when it is retrieved form the remote database. Hitting the local database query button will show you part of data retrieved from local database.
- Bluetooth setting up:
   1. First, turn on the bluetooth option, then turn on the discoverability option, this will make your device discoverable to other devices for 600 seconds;
   2. After being ready, hit discover button and chose the device you want to pair with;
   3. Once get successfully paired, you may hit the connect button and send the message, note that all device has to install this application to work properly on this feature;
   4. If either one of the applications exits the bluetooth interface, the connection will be lost, so the other one would also exit and end up the old connections and start with a new one.
