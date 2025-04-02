# FamConnect: Family contacts simplified

## About my project

**What will the application do?**

- The *"FamConnect"* software is designed to serve 
as a comprehensive family contact and associated 
event management system. Users will have the ability 
to effortlessly add individuals to their database, 
complete with specifying their familial relation, and 
inputting essential details such as birthdays, emails, 
phone numbers, and more. Users also have the choice 
to define and create events like graduation dates or 
significant milestones. 
- The software empowers users to seamlessly update 
contact details, establish new familial relationships, 
and introduce new individuals into the system. Last 
but not the least, the user is also given the option 
to update information and delete content from the 
database as desired. This ensures that family data 
remains consistently up-to-date and readily accessible 
for future interactions. An important aspect to be 
noticed in this project is the ***CRUD (CREATE, READ, UPDATE, DELETE)*** format used in the
blueprint design of this system.

**Who will use it?**

- The *"FamConnect"* project is tailored to meet the 
needs of a diverse user base. It targets individuals 
who hold family connections in high regard and are in 
search of a practical solution to manage family events 
and contacts. This encompasses individuals with joint 
family structures, those who encounter challenges in 
keeping track of various family events, and those who 
may not feel entirely at ease with currently available 
apps for this purpose, such as senior citizens.
- "FamConnect" aspires to serve as a widely 
accessible, inclusive and user-friendly tool for 
anyone seeking to maintain and strengthen their 
family ties through efficient information management.

**Why is this project of interest to me?**

- The *"FamConnect"* project holds profound personal 
significance for me, as it embodies the cultural values
and dynamics of my upbringing. I originate from a 
background where close-knit, extended family structures 
are the norm. The art of staying connected and staying 
informed about each other's lives is ingrained in our 
cultural fabric. In the rapidly evolving landscape of 
data and information, the need for effective 
information management and tracking has become 
increasingly paramount. 
- On a personal level, I recognize that such a system 
would significantly enhance the convenience and quality 
of my own life. My *passion* for this project extends 
beyond a mere academic endeavor; it's a heartfelt 
commitment to create a tool that not only caters to my 
own needs but also serves as a valuable resource for a 
diverse range of users facing similar familial 
circumstances.
- *"FamConnect"* represents my aspiration to bring 
families closer together, bridging the gap between 
traditional values and modern lifestyles in today's 
fast-paced world.

## User Stories

- As a user, I want to be able to add new 
individuals (names) to my family contact database. 
(**ADDING**) 
- As a user, I want to create and include details
(related to the individual) such as the relationship, 
birthday, email, phone number, and events 
(e.g., graduation dates) unique to the person when 
adding contacts. (**ADDING**)
- As a user, I want to view a list of all the 
contacts and the information associated with 
each of them - including the list of events associated
with the person - in my family database. (**VIEWING**)
- As a user, I want the ability to update 
contact information, such as phone numbers, events, 
email addresses, or more for any individual in my 
database. **(CUSTOM)**
- As a user, I want the ability to delete
contacts and their associated details from my
database. **(CUSTOM)**
- As a user, I want the ability to search
for contacts and their associated details from my
database based on a certain criteria. **(CUSTOM)**
- As a user, I want the ability to see the contact(s) whose
birthday(s) is/are upcoming. **(CUSTOM)**
- As a user, when the application's main menu is
displayed, I want to be given the option of saving
the contacts and their associated details so far to
a file. **(REQUIRED/SAVING)**
- As a user, when I start the application, I want to
be given the option of (re)loading the contacts and
their associated details added so far from the file,
from the previous run, and resume from there.
**(REQUIRED/LOADING)**
- As a user, when I exit the application, I want to 
view a (logged) console which displays all the 
key events performed during that session of the app.
**(REQUIRED/LOGGING)**
## In phase 1:
- As a user, I want to be able to add new
individuals (names) to my family contact database.
(**ADDING**)
- As a user, I want to create and include details
(related to the individual) such as the relationship,
birthday, email, phone number, and events
(e.g., graduation dates) unique to the person when
adding contacts. (**ADDING**)
- As a user, I want to view a list of all the
contacts and the information associated with
each of them - including the list of events associated
with the person - in my family database. (**VIEWING**)
- As a user, I want the ability to update
contact information, such as phone numbers, events,
email addresses, or more for any individual in my
database. **(CUSTOM)**

## In phase 2:
- As a user, when the application's main menu is 
displayed, I want to be given the option of saving 
the contacts and their associated details so far to 
a file. **(REQUIRED/SAVING)**
- As a user, when I start the application, I want to 
be given the option of (re)loading the contacts and 
their associated details added so far from the file, 
from the previous run, and resume from there.
**(REQUIRED/LOADING)**
- As a user, I want the ability to delete
contacts and their associated details from my
database. **(CUSTOM)**

## In phase 3:
- As a user, I want the ability to search
for contacts and their associated details from my
database based on a certain criteria. **(CUSTOM)**
- As a user, I want the ability to see the contact(s) whose
birthday(s) is/are upcoming. **(CUSTOM)**

## In phase 4:
- As a user, when I exit the application, I want to
view a (logged) console which displays all the
key events performed during that session of the app.
**(REQUIRED/LOGGING)**

# Instructions for Viewer
- NOTE FOR VIEWER: RUN THE **'SplashScreen'** CLASS TO SEE ALL 
REQUIRED ACTIONS. THEN, SEE BELOW FOR FURTHER INSTRUCTIONS.
- You can generate the first required action related to 
the user story "adding multiple Xs to a Y" by clicking on
the 'Add Contact' button on the GUI. Here, you can add 
multiple 'Person' objects along with its subsequent 
details to a list of contacts. Apart from basic details 
such as the relation or phone number, you can also add 
multiple events associated with 'Person' objects, using 
objects of the 'Event' class, and unique to each 'Person' 
object. The 'Save contact' and 'Save event' buttons need
to be clicked to save the details to the list of contacts.
- You can view the panel in which "all the Xs that have
already been added to Y" are displayed by clicking on 
the 'View Contacts' button on the GUI. Here, you can 
view a list of all the 'Person' objects added so far/loaded
from a previous run. The 'Person' objects are displayed 
including all details as well as the details all the 
subsequent unique 'Event' objects associated with each 
Person object, if any. The details of each 'Event' object 
include an event name, an event date and an event description.
- You can generate the second required action related to the 
user story "displaying a subset of the Xs" by clicking on the
'Search by relationship' button on the GUI. Here, you can enter 
a relation and all contacts (along with their details and associated
events and their details) with that relation are displayed. The 
search bar is not case-sensitive.
- You can locate my visual component in the first few seconds
before the main GUI opens and runs. In the first few seconds,
a splash screen with a strikingly colourful and constantly 
changing image, a welcome message and a loading bar pops up. 
Once the loading bar is complete and the main GUI opens, this 
splash screen closes.
- You can save the state of my application by clicking on 
the 'Save Contacts' button on the GUI. This will save all the 
contacts added so far to a JSON file.
- You can reload the state of my application by clicking on
the 'Load Contacts' button on the GUI. This will load all the
contacts (which were saved to a JSON file in a previous run) 
from a JSON file.
- You can generate another action related to the
user story "highlight most important X" by clicking on the
'Show upcoming birthday' button on the GUI. Here, you can see
the contact (the name and birthday only) or contacts whose 
birthday(s) is/are upcoming next. The person whose birthday 
is upcoming next is displayed. If more than one person's 
birthday is upcoming, they are both displayed.

## Phase 4: Task 2
- Here is a representative sample of the events that 
occur when my program runs - <br> <br>
Events logged during this session: <br>
Thu Nov 30 03:29:35 PST 2023<br>
&nbsp; &nbsp; &nbsp;Loaded the contacts from a JSON file.<br>
Thu Nov 30 03:29:40 PST 2023<br>
&nbsp; &nbsp; &nbsp;Loaded the contacts from a JSON file.<br>
Thu Nov 30 03:29:41 PST 2023<br>
&nbsp; &nbsp; &nbsp;Added a contact with details as needed.<br>
Thu Nov 30 03:30:15 PST 2023<br>
&nbsp; &nbsp; &nbsp;Searched for contact(s) by specifying a relationship: friend<br>
Thu Nov 30 03:30:19 PST 2023<br>
&nbsp; &nbsp; &nbsp;Searched for contact(s) by specifying a relationship: brother<br>
Thu Nov 30 03:30:21 PST 2023<br>
&nbsp; &nbsp; &nbsp;Viewed the contact(s) with the nearest upcoming birthday(s).<br>
Thu Nov 30 03:30:24 PST 2023<br>
&nbsp; &nbsp; &nbsp;Saved the contacts to a JSON file.<br>

## Phase 4: Task 3
- In my project, I find that I had to code the 
GUI part of my application, along with its complete 
functionality, almost entirely from scratch. Apart from 
the code for the saving and loading features from 
the classes and methods of the persistence package, 
no other part of my code was practically reusable and useful 
for building the GUI as in Phase 3 of the project. Hence, 
the aim of incrementally developing the code for my project
was defeated. I could, however, use the logic and ideas from the code written in 
previous developmental phases.
- If I had more time to work on the project, I would 
increase the code reusability by making use of inheritance
principles. Ideally, the code developed during initial 
phases should have served as a base for further development.
This is a design flaw in my application, which can certainly
be improved upon in the future.


