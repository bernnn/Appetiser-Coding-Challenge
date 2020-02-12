# Appetiser-Coding-Challenge

What I have on this project are as follows:


Persistence

For data persistence I created a simple mechanism using sharedpreference to save the last date/time the app made succesfull api request. I used the functionality of retrofit for caching the data. So that if the device has no network connection and the user opens the app, the cached data will be the one to be displayed, and the saved date/time the app made a successful api request will be displayed.


Architectrure

For the architetcure I decided to go with mvvm for the master module, because I wanted to practice coding aligned with what google is recommending regarding design pattern. For the detail module I decided to just go with single class activity, because its task is just to show a simple detail and for the sake of coding it the simplest way.
