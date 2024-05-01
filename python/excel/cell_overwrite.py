from xlwt import Workbook

#Normally the library raise and exception in case of overriding a cell.
#Its possible to change this behavior permitting cells overwrite with specific options on worksheet
book = Workbook()

sheet1 = book.add_sheet('Sheet 1',cell_overwrite_ok=True)
sheet1.write(0,0,'original')
sheet = book.get_sheet(0)
sheet.write(0,0,'new')


sheet2 = book.add_sheet('Sheet 2')
sheet2.write(0,0,'original')
sheet2.write(0,0,'new')