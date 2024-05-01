
from xlrd import open_workbook


wb = open_workbook('example.xls')

#sheets of the workbook
for s in wb.sheets():
    #sheet name
    print('Sheet:',s.name)
    #number of rows in the sheet
    for row in range(s.nrows):
        values = []
        #number of columns in the sheet
        for col in range(s.ncols):
            #cell return the content of a particular cell
            cell = s.cell(row,col)
            #cell has attribute value containing the actual value
            #cell has attribute ctype containing the type of the cell
            values.append(cell.value) #cell.ctype
            #faster alternative to get value or type directly
            #s.cell_type(row,col)
            #s.cell_value(row,col)
        print(values)

#number of sheets in the workbook
for sheet_index in range(wb.nsheets):
    print(wb.sheet_by_index(sheet_index))

#list of names of the sheets
for sheet_name in wb.sheet_names():
    #retrieve the sheet by his name
    print(wb.sheet_by_name(sheet_name))

sheet1 = wb.sheet_by_index(0)

#retrieve full row
print(sheet1.row(0))
print(sheet1.row(1))
print(sheet1.row(2))

#retrieve full column
print(sheet1.col(0))
print(sheet1.col(1))
print(sheet1.col(2))

#slice notation
print(sheet1.row_slice(0,1))
print(sheet1.col_slice(0,1,2))

#slice notation on types and values
print(sheet1.row_values(0,1,2))
print(sheet1.col_values(0,1,2))
print(sheet1.row_types(0,1))
print(sheet1.col_types(0,1))