#  GodotLocalNotification
This is Android local notification module for [Godot Engine](https://github.com/okamstudio/godot "Godot Engine") 3,2+. For older version use [DrMoriarty](https://github.com/DrMoriarty/godot-local-notification "DrMoriarty/godot-local-notificatoin")'s custom module.

##  How to use
- Enable "Android Custom Template" in your project (follow this [Official Documentation](https://docs.godotengine.org/en/latest/getting_started/workflow/export/android_custom_build.html "Official Documentation")).
- Download or Clone this repository
- Drop the `localnotification` directory inside `res://android/` directory
- Add this string inside your Project Setting->Android->Modules. (use comma to separate more than one modules)

		org/godotengine/godot/GodotLocalNotification

------------

## Custom notification color
- Go to  the directory `res://android/localnotification/src` 
- Open  `LocalNotificationReceiver.java` file
- Find `builder.setColor(0xff0000ff)` line, uncomment it and use custom `argb` color values.
### How to change ARGB values
0xff0000ff is Blue color
(Here, 0x**ff**0000ff - alpha;
0xff**00**00ff - red; 
0xff00**00**ff - green;
0xff0000**ff** - blue)

------------

### Do you love this module?
If you want to support me, just download my puzzle game [cress](http://bit.ly/cresspuzzle "cress on Google play"), I made using this module (You can check out whether this module working or not, May be)

------------

## Reference
```python
# Show Banner
# @param string message The notification message
# @param sting title Notification title
# @param int interval The time out interval in seconds
# @param int tag The notification tag (Use the same id to override previous notification)
show_local_notification(message, title, interval, tag)


# Cancel the shown notificaton with tag
# @param tag The tag of the notification
cancel(tag)


# Cancel all shown notifications having different tags
cancel_all()


# Stop last fired notification
stop_last_notification()
```

------------


## Example:
```python
var ln
func _ready():
	if(Engine.has_singleton("GodotLocalNotification")):
		ln = Engine.get_singleton("GodotLocalNotification")

#events
func _on_Button_pressed():
	var message = "Hola..!!"+ str(OS.get_time())
	var interval = 5 # 5 seconds
	var tag = 1 # tag is 1
	ln.show_local_notification(t,"My title 1",interval,tag)

func _on_Button2_pressed():
	var message = "Hello ..!!"+ str(OS.get_time())
	var interval = 60 # 1 minute
	var tag = 2 # tag is 2
	ln.show_local_notification(t,"My title 1",interval,tag)

func _on_Button_Cancel_One_pressed():
	var tag = 1 #Close the shown notification which have tag 1
	ln.cancel(tag)

func _on_Button_Cancel_All_pressed():
	ln.cancel_all() #this will cancel all notifications having different tags

func _on_Button_stop_last_pressed():
	ln.stop_last_notification() #This will stop recently fired notification before the interval

```

------------
