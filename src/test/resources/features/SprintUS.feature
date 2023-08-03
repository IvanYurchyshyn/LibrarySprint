Feature: As a data consumer, I want the user information are stored in mySql DB correctly in users table.

  Background:
    Given Establish the database connection

  Scenario: verify users has unique IDs
    When Execute query to get all IDs from users
    Then verify all users has unique ID

  Scenario: verify users table columns
    When Execute query to get all columns
    Then verify the below columns are listed in result

      | id |
      | full_name |
      | email |
      | password |
      | user_group_id |
      | image |
      | extra_data |
      | status |
      | is_admin |
      | start_date |
      | end_date |
      | address |

Feature: As a librarian, I want to know borrowed books number

  Scenario: verify the total amount of borrowed books
    Given the "librarian" on the home page
    When the librarian gets borrowed books number
    Then borrowed books number information must match with DB

Feature: As a data consumer, I want UI and DB book categories are match.

  Scenario: verify book categories with DB
    Given the "librarian" on the home page
    When the user navigates to "Books" page
    And the user clicks book categories
    Then verify book categories must match book_categories table from db

Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page
    When the user searches for “Clean Code” book
    And the user clicks edit book button
    Then book information must match the Database

Feature: As a data consumer, I want to know genre of books are being borrowed the most

  Scenario: verify the the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Action and Adventure" is the most popular book genre.

Feature: Books module
  As a librarian, I should be able to add new book into library

  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page
    When the librarian click to add book
    And the librarian enter book name "<Book Name>"
    When the librarian enter ISBN "<ISBN>"
    And the librarian enter year "<Year>"
    When the librarian enter author "<Author>"
    And the librarian choose the book category "<Book Category>"
    And the librarian click to save changes
    Then verify “The book has been created" message is displayed
    And verify "<Book Name>" information must match with DB

    Examples:
      | Book Name | ISBN | Year | Author | Book Category |
      | Clean Code | 09112021 | 2021 | Robert C.Martin | Drama |
      | Head First Java | 10112021 | 2021 | Kathy Sierra | Action and Adventure |
      | The Scrum Field Guide | 11112021 | 2006 | Mitch Lacey | Short Story |


Feature: Books module
  As a students, I should be able to borrow book

  Scenario: Student borrow new book
    Given the "student" on the home page
    And the user navigates to "Books" page
    And the user searches book name called "Head First Java"
    When the user clicks Borrow Book
    Then verify that book is shown in "Borrowing Books” page
    And verify logged student has same book in database