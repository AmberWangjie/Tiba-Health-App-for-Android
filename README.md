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

