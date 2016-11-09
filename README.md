# logviewer

Imagine we are creating a log-viewer tool which receives events at random intervals containing the value 1,2 or 3.
1 is a good result :), 2 a warning :| and 3 a bad result :(

1)	Add a list view showing all events.
2)	Customize the rows in a way you find appropriate. Use the smileys (found in res/drawable) to indicate the event instead of the number
3)	Use the LoggingActivity.addLog() method to populate your list instead of the exampleList

4) Add a description page which is shown when you press a tablecell

5)	Add some sort of filtering of the list, for example based on
    a.	Type of event (1,2 or 3)
    b.	Time of receiving the event (within the last 10 seconds; from 3 second to 35 seconds after starting the log viewer, etc)
    You decide how to initiate the filtering, buttons or whatever.
6)	Freestyle