# My Personal Project


## Song Cover Organizer.

My application will be the hub for all the videos covered/performed by talented or casual musicians who are interested in keeping up to date 
on their video analytics and overall growth as a music content creator. Hub, in this case, refers to a place where raw data,
including but not limited to, video title, featured instrument, view count, like/dislike count, is stored and, upon request, converted into meaningful information. 
This information will be in the form of a generated list that to shows the top x, where x is specified by the user, most viewed, liked or disliked videos and 
graphs detailing key analytics over a specified period. Alongside the addition and deletion of data, favoriting videos that can be displayed in a list will also be a feature.
Such information could potentially improve the quality of future videos as they taylor their style to match their audience's wants and needs.

This project interests me because I have been playing acoustic guitar for three years (self-taught) and 
have only recently worked up the courage to record myself performing video covers as a way to archive my progress and to 
see how much I have improved; this thirst for improvement which coincides with **the goal** of this project which is to facilitate progression
for musicians of all kinds.

## User Stories

- I want to be able to **add** a video to my video database
- I want to be able to **delete** a video from my video database
- I want to be able to **search** for a video based on video title.
- I want to be able to **favorite** videos in the video database.
- I want to be able to **see** a list of all the videos in my video database.
- I want to be able to **filter** my video covers by the instrument featured.
- I want to be able to **calculate** the average amount of views, likes and dislikes my video covers have.
- I want to be able to **sort** all my videos in ascending/descending order based on view, like or dislike count and be able to limit how many entries they see. 
- I want to be able to **generate** a line graph that shows my lifetime career performance based on view count 
- I want to be able to **save** any changes I make while using the application
- I want to be able to **load** these changes when I reopen the application

## Instructions For Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the **Add New Song button** where you will be prompted to input the relevant fields one after the other.
- You can generate the second required action by related to "displaying a list of added Xs to Y" by clicking the **List Songs button**.
- You can generate the third action by clicking the **Filter Songs button** which will prompt you to input the name of an instrument and filter the list of Xs that have that instrument.(You can click the **List All Songs button** to view the **Instrument field** to know what instruments exist in the list).
- You can generate the fourth action by clicking the **Delete Song button** which will prompt you to input a video to delete based on video name. (You can click the **List All Songs button** and viewing the **Song Name** field to see what videos can be deleted).
- You can generate the fifth action by clicking the **Favourtie A Song button** which will prompt you to input a video to favourite based on video name. Clicking the **List All Songs button** will allow you to see this update.
- You can generate the sixth action by clicking the **Unfavourtie A Song button** which will prompt you to input a video to unfavourite based on video name. Clicking the **List All Songs button** will allow you to see this update.
- You can locate my visual component by clicking the **List All Songs button** and viewing the **Favourite** field: If a video is favourited, a "<img src="./data/favourited.png" height="15" width="15">" icon is seen else "<img src="./data/unfavourited.png" height="15" width="15">" icon is seen
- You can save the state of my application by clicking the **Save Data button** then ok
- You can reload the state of my application by clicking the **Load Data button** then ok however, do note that this will overwrite the current state of the application if any updates were made prior to clicking the load button

## "Phase 4: Task 2"
Events Logged...

Fri Dec 01 11:56:42 PST 2023\
Added Song:model.Video@7d794c91

Fri Dec 01 11:56:55 PST 2023\
Added Song:model.Video@5bbd60f1

Fri Dec 01 11:57:06 PST 2023\
Added Song:model.Video@771986c8

Fri Dec 01 11:57:16 PST 2023\
Favouritied Song: model.Video@5bbd60f1

Fri Dec 01 11:57:22 PST 2023\
Unfavourited Song: model.Video@5bbd60f1

Fri Dec 01 11:57:39 PST 2023\
Song: model.Video@7d794c91 was added to the filtered list

Fri Dec 01 11:57:39 PST 2023\
Song: model.Video@771986c8 was added to the filtered list

Fri Dec 01 11:57:39 PST 2023\
Displayed Filtered Song List Based On: Guitar:[model.Video@7d794c91, model.Video@771986c8]

Fri Dec 01 11:57:44 PST 2023\
Deleted Song:model.Video@5bbd60f1

## "Phase 4: Task 3".

Reflecting on my UML diagram for this application, I noticed the class called "SongDatabaseState" has numerous dependency arrows going towards it. 
My intention with this class was to allow my entire GUI to have access to the current state of the "SongDatabase" class so that any updates made by the user can be known throughout 
the "ui" package as numerous classes need to know its current state to run effectively. After learning about the "Singleton" class concept from the "Design" module of this course,
I would refactor the "SongDatabaseState" class into a singleton class so that each of my "-Action" classes can get the current state of the "SongDatabase" class; namely using the "getInstance()" method.
This would remove all dependency arrows going into the "SongDatabaseState" class. 

Another fix that I could have done was remove the "SongDatabaseState" class entirely and implement the "Observer Pattern" namely between "LoadAction" (the subject) and "SongOrganizerAppUI" (the observer);
the subject would notify the observer that previous data was loaded into the "SongDatabase" field in the LoadAction class by the user and that to update its current "SongDatabase" field to the new one in the "SongOrganiserUI" class. This would have reduced the number of dependencies in the original application.



  