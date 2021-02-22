# cs441-proj2-android
CS441 Assignment 2 - Multi-screen App

# Graph Library:
## https://stackoverflow.com/questions/35238770/android-how-to-display-bargraph-in-android
## https://github.com/PhilJay/MPAndroidChart
## https://weeklycoding.com/mpandroidchart-documentation/getting-started/

# Swiping:
## https://developer.android.com/training/gestures/detector#java
I started here and got basic movement/down presses working, but didn't understand how I could check direction, 
as it wasn't a function I could override.

## https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
I used the second answer as a jumping off point, but made it a bit simpler (I didn't need anything super-accurate). This was a big help, and I focused on understanding how/why it worked rather than just copying it. Thus, I've added comments to my version.

## https://stackoverflow.com/questions/4486034/get-root-view-from-current-activity
How to get the view for the MainActivity, needed to call SwipeListener

# Activities and Contexts
## https://stackoverflow.com/questions/18049284/how-to-go-to-an-already-existing-activity-from-a-different-one/18049394
How to open existing activity, not make a new one
