# The Innkeeper
Program that makes it easy to record data and print reports regarding the finances an apartment building.  

## Table of Contents
* [Getting Started](#getting-started)
* [How To Add Your Code](#how-to-add-your-code)
* [Request For Proposal](#request-for-proposal)
* [Problem Statement](#problem-statement)
* [Vision Statement](#vision-statement)
* [Use Case & Scenarios](#use-case-and-scenarios)
* [UML Diagrams](#uml-diagrams)
  
## Getting Started
### Prerequisites
You will need to have git installed on your computer. From the terminal, navigate to your local git repository folder.
```
$ cd Documents/[your local git directory folder]/
```
Clone the remote repository to your local git repository folder.
```
$ git clone https://github.com/jerrybelmonte/Innkeeper-System.git
```
Verify that the process was successful. Check and make sure you have all the project files in your local git repository.
### Installing
Open up Eclipse IDE and change from the default window to the Git repository window.
```
Window > Show View > Git Repositories > Add an existing local Git repository to this view
```
Navigate to the directory where you cloned the remote repository and add the local git repository.
```
Browse.. > Directory: /Inkeeper-System.git > Add > Selectect Inkeeper-System
```
Import the git project to your Eclipse IDE workspace.
```
Right-click the Inkeeper-System Working Tree folder > Import Projects > Import Source > Finish
```
Configure the build path for the project, need to configure build path to run the unit tests.
```
Right-click the Inkeeper-System project > Build Path > Configure Build Path > Libraries Tab > Classpath > Add Library > JUnit > JUnit5 Library > Finish
```
Once you configure the modulepath, you should be able to run the TenatTest file in Eclipse.
```
Inkeeper-System > src > test > Right-click the TenantTest.java file > Run As > 1 JUnit Test
```

## How To Add Your Code

* Create a new branch for your feature with 
```
git checkout -b <[feature-name]>
``` 
* Check your local branch and the remote branches with 
```
git branch -a
```
* Track a remote branch on the GitHub repository 
```
git branch <[branch-name]> origin/<[branch-name]>
```
* To check if your branch is configured to push upstream to a remote branch
```
cat .git/config
```
* Go to the newly created branch if necessary with 
```
git checkout <new-or-existing-branch-name>
```
* If you wish to view the 10 most recent commits from all the current branches
```
git log --graph --all --oneline -10
```
* To check and see the commits from all the collaborators
```
git shortlog
```
* Write/add your code changes to git staging before making a commit
```
git add .
``` 
* After your changes are in the git staging area, commit your changes and add a short message, i.e. "_If applied, this commit will_...**Fix bug when calculating annual summary** "
```
git commit -m "<short-but-descriptive-commit-message>"
```
* Before you `$ git push` to the master branch, make sure to `$ git fetch` to get the latest updates from master, this helps avoid potential merge conflicts.


##### REALLY IMPORTANT: **`$ git pull origin master` into your own branch, to fix any conflicts before you push to the remote (to GitHub)**


* Then `$ git commit` any of the conflicts you fixed (if you had them)
* Once there are no more conflicts, `$ git push origin <your-branch-name>`
* Go to the Github page (in your browser) and see the comment that says: "you recently .... do you want to create a Pull Request?"
* Select to merge into the master-branch, write what you changed/made in the message, etc.
* Create the Pull Request

## Request For Proposal
John Nguyen is a small-time landlord: He owns an apartment building with 20 units. He wants us to write a program that will make it easier for him to record data and print reports regarding the finances of the apartment building. If we can agree with John on payment, schedule, and the overall purpose of the program, we've completed the inception part of the development process.  
  
Currently John is recording all the information about his apartment building by hand, in old-fashioned ledger books. He shows us the forms he's currently using. There are four of them:  
1. The Tenant List
2. The Rental Income Record
3. The Expense Record
4. The Annual Summary
  
The Tenant List shows apartment numbers in one column and the corresponding tenant's names in the adjacent columns.  
  
The Rental Income Record records incoming rent payments. It contains 12 columns, one for each month; and one row for each apartment number. Each time John receives a rental payment from a tenant, he records it in the appropriate row and columns of the Rental Income Record.  
  
The Expense Record records outgoing payments. It has columns for date, the payee (the company or person to whom John writes the check), and the amount being paid. In addition , there's a column where John can specify the budget category to which the payment should be charged. Budget categories include Mortgage, Repairs, Utilities, Taxes, Insurance, and so on.  
  
The Annual Report uses data from the Rental Income Record and Expense Record to summarize how much money came in and how much went out during the year. All the rents are summed and the result is displayed. The expenses are summed and displayed by the budget category, which makes it easy to see, for example, how much was spent on repairs during the year. Finally, expenses are subtracted from income to show how much money John made (or lost) during the year.  
  
John tells us that he wants the program to pretty much duplicate what he's currently doing on the paper forms. He wants to be able to enter data about tenants, rents, and expenses, and display the various reports.  

## Problem Statement
John finds it tedious to calculate the rental income and expenses by hand for over 20 housing units. This problem affects John because John unnecessarily spends too much time on making records by hand when he could use that time for other tasks. A successful solution to John’s problem and tedious work would be a way to easily track specific records, save John time from paper-bookkeeping, better overall organization, a method for faster calculations, and to make data easily transferable (mobile).  

## Vision Statement
A product has been made for John. A way for easier, faster, and more organized bookkeeping with less room for error. This product is called The Innkeeper, a bookkeeping software program. The benefits of The Innkeeper is that there is no longer a need for paper records, the software does all the hard work and number crunching for him and that it is very simple and straightforward, ultimately saving John a lot of time. Unlike our competitor, Excel, our product offers a more optimized way to complete bookkeeping tasks. As opposed to more broad data entry software programs, we offer an easier and more specified approach.  
  
## Use Case and Scenarios
### Use case number: 1
#### Use case name: Log In
__Summary:__ The actor logs into the system to manage his apartment building. When he wants to record data or print a report, he first has to log into the program to verify that it is him and not an external actor.  
__Actor:__ John  
__Precondition:__ John is sitting at his desk and turns on the computer. They start the program and are going to record rent payments that they received for the current month. Or they need to print an annual report to assess current financial status.  
__Scenario:__ John wants to record an expense record for one of the units because he had to hire a plumber to fix the sink.  
__Exception:__ John forgot his password and has issues with the login screen.  
__Postcondition:__ John successfully enters his login information and goes to the next window that displays a menu.  
__NonFunctional:__ Authenticate that the user is permitted to use the system. When the system starts up, it should have read the record log files in the background so that John could start using the program as soon as he logs in.  

### Use case number: 2
#### Use case name: Start the Program
__Summary:__ John needs to perform an action like record data or print a report, there is a main menu with options of the user requirements specified by John to manage his apartment building finances.  
__Actor:__ John  
__Precondition:__ John successfully logs into the system. Now he is at the main menu.  
__Scenario:__ John wants to see the annual report at the end of the year so that he could compare with the previous year and budget for the upcoming new year.  
__Exception:__ John wants to do something different than what the menu displays as an option, like a feature that was not communicated or discussed when John made his proposal for the software product.  
__Postcondition:__ John selects a menu option to do a certain task, such as record data or print a report.  
__NonFunctional:__ The system product has to access the files to do the necessary operations, such as printing reports, and be able to write transaction files. It also should be able to process the data and manipulate it to display tables with specific data labeling the rows and columns.  
 
### Use case number: 3
#### Use case name: Add a new tenant 
__Summary:__ John enters a new tenant’s name and apartment number to the Tenant List  
__Actor:__ John  
__Precondition:__ INNKEEPER is displaying the main menu  
__Scenario:__ Danny just moved in apartment number 14, John wants to add a Danny to the Tenant List  
__Exception:__ A new couple just moved in an apartment but they share the rent and want to pay their part directly to John, John want to add both of them to the Tenant List  
__Postcondition:__ A new tenant has been added to the Tenant List  
__NonFunctional:__ If there is already a tenant in the entered apartment number, the system displays an error message, ask to re-enter the new tenant or go back to the main menu  
 
### Use case number: 4
#### Use case name: Input a rental tenant
__Summary:__ John enters the rental information of a tenant to the Rent Record  
__Actor:__ John  
__Precondition:__ INNKEEPER is displaying the main menu  
__Scenario:__ Danny want to pay $700 as the rent for March, Johns wants to add that rental information to the Rent Record  
__Exception:__ A couple live in the same apartment but they share the rent and want to pay their part directly to John, John want to add their rental information separately in the Rent Record  
__Postcondition:__ A new rental information of a tenant has been added to the Rent Record  
__NonFunctional:__  If the tenant already paid the rent for the entered month, the system displays an error message, ask to re-enter the rental information or go back to the main menu  
 
### Use case number: 5
#### Use case name: Display Tenant List
__Summary:__ John is able to display a list of Tenant information. Including tenant name and apartment number.  
__Actor:__ John  
__Precondition:__ INNKEEPER is displaying the main menu  
__Scenario:__ A tenant asks John for their apartment number, John pulls up the organized Tenant List and is able to inform the tenant of their number.  
__Exception:__ John views the display tenant page and notices a typo, he goes back and corrects the error.  
__Postcondition:__ John returns to the main menu.  
__NonFunctional:__ Inputting new records into the system.  
 
### Use case number: 6
#### Use case name: Display Rent Records
__Summary:__ John is able to display a list of Rent Records. Including apartment number and monthly rent cost.  
__Actor:__ John  
__Precondition:__ INNKEEPER is displaying the main menu.  
__Scenario:__ A tenant pays John for their monthly rent. John thinks the value is too low. John displays rent records and is corrected when he sees that the tenant has paid the right amount.  
__Exception:__ John views the display page and notices a typo, he goes back and corrects the error.  
__Postcondition:__ John returns to the main menu.  
__NonFunctional:__ Inputting new records into the system.  
 
### Use case number: 7
#### Use case name: Display Expense Record
__Summary:__ John will be given an option where he can see a list of expense records he has filled out earlier.  
__Actor:__ John  
__Precondition:__ Display a menu option that lists “Display Expense Record” as one of the choices.  
__Scenario:__ John wants to see a list of expense records in an organized row so he can easily track his expenses.  
__Exception:__ If the expense is not filled in, the program will notify John about the missing box.  
__Postcondition:__ John returns to the main menu.  
__NonFunctional:__ Can’t input new records while the list is being displayed.  
 
### Use case number: 8
#### Use case name: Display Annual Summary.
__Summary:__ A list of annual summary will be shown. Such as, sum of all rents paid for the year to date, total expenses for each budget category, and the resulting balance (profit or loss for the year to date)  
__Actor:__ John  
__Precondition:__ Display a menu where “Display Annual Summary” is an option.  
__Scenario:__ John would like to see a list of his sum of all rents by over 20 units, total expenses, and the resulting balances in a nice format. Simple and faster way for John to check if there were any missing payments or errors.  
__Exception:__ If John sees a blank input, he must go back to the menu and re-input all the new values.  
__Postcondition:__ John returns to the menu.  
__NonFunctional:__ Can’t input new records while the list is being displayed.  

## UML Diagrams
### Use Case Diagram
![alt text](https://github.com/jerrybelmonte/Innkeeper-System/blob/master/Images/UseCaseUML.jpg?raw=true)
### Overall Class Diagram
![alt text](https://github.com/jerrybelmonte/Innkeeper-System/blob/master/Images/OverallClassDiagram.jpg?raw=true)
