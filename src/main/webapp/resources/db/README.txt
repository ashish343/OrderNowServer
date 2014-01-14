How to set up DB:
Pt these files in bin forder of mongo installation.
Execute:
C:\mongodb\bin>mongoimport -d OrderNow -c table_rest --type csv --file table_rest.csv --headerline
C:\mongodb\bin>mongoimport -d OrderNow -c rest --type csv --file rest.csv --headerline
C:\mongodb\bin>mongoimport -d OrderNow -c order --type csv --file order.csv --headerline