# StackTrack - A Library Management System
<!--Badges: License Badge-->
<p>StackTrack is  a Library Management System designed to streamline the process of managing library users and books.</p>
<p> It enables administrators/librarians to efficiently track and manage book circulation, user accounts, and overall library operations.</p>
<p>In additon to operations catered towards adminsitrators/libarains, StackTrack includes the ability for library patrons to manage and view their account information.</p>


## Built With
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java_Swing-db2f2d?style=for-the-badge)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Features
<!--List the main features or functionalities of project-->
- Librarian/Admin Features
    - Manage Users
        - Add Users
        - View Users
    - Manage Books
        - Add Books
        - View Books
        - Issue Books
        - View Issued Books
        - Return Books
        - View Returned Books
- User Features
    - Manage Books
        - View Books
        - View Issued Books
        - View Returned Books


## Sceenshots
<!--Photos of App: Login, Signup, Librarian Frame, LibarianFrame, UserFrame, Add User, Manage Users, Add Book, Manage Books-->
### Login
![StackTrack - Login](src/main/java/com/dh/pictures/lms_login.png)
### Signup
![StackTrack - Signup](src/main/java/com/dh/pictures/lms_signup.png)
### Librarian/Admin Frame
![StackTrack - Admin Dashboard](src/main/java/com/dh/pictures/lms_admin_dashboard.png)
#### View Books
![StackTrack - Admin Frame for Books](src/main/java/com/dh/pictures/lms_admin_view_books.png)
#### Add Books
![StackTrack - Admin Frame Adding Books](src/main/java/com/dh/pictures/lms_admin_add_book.png)
#### View Users
![StackTrack - Admin Frame for Viewing Users](src/main/java/com/dh/pictures/lms_admin_view_users.png)
#### Add Users
![StackTrack - Admin Frame for Adding Users](src/main/java/com/dh/pictures/lms_admin_add_user.png)
### User Frame
![StackTrack - User Dashboard](src/main/java/com/dh/pictures/lms_user_dashboard.png)
#### View Issued Books
![StackTrack - User Frame for Viewing Issued Books](src/main/java/com/dh/pictures/lms_user_view_issued_books.png)

# Project Roadmap
<p>What's Been Completed:</p>

- Ability for Admin to add database entries for "books", "issued_books", "returned books", and "users"
- Ability for Admin to view database entries for "books", "issued_books", "returned books" and "users"
- Ability for User to view database entries for "books", "issued_books" (related to their UID), and "returned books" (related to their UID)
 <p>Potential Add-ons:</p>

- Ability for Admin to edit database entries for "books", "issued_books", "returned books", and "users"
- Ability for Admin to delete database entries for "books", "issued_books", "returned books", and "users"
- Ability for Admin and User to change color scheme of application

# License
<p>Distributed under the GNU GPLv3 License. See the "COPYING" file for more information.</p>