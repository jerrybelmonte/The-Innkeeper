# Innkeeper System
Program that makes it easy to record data and print reports regarding the finances an apartment building.

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
