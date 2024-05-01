
import xlwt
from datetime import datetime

def main() :
    style0 = xlwt.easyxf('font: name Times New Roman, color-index red, bold on',
        num_format_str='#,##0.00')
    style1 = xlwt.easyxf(num_format_str='DD-MM-YY')

    #main entity of the library
    wb = xlwt.Workbook()

    #worksheet of the workbook
    ws = wb.add_sheet('Sheet 1')

    #write create a cell -> (x, y, value, style)
    ws.write(0, 0, 1234.56, style0)
    ws.write(1, 0, datetime.now(), style1)
    ws.write(2, 0, 1)
    ws.write(2, 1, 1)
    ws.write(2, 2, xlwt.Formula("A3+B3"))

    #retrieve a woprksheet from the workbook using the name or the index notation
    ws = wb.get_sheet(0)
    #working with rows -> use the index notation in the sheet
    #write on a row -> (y, value, style)
    ws.row(10).write(0, "A1")
    ws.row(12).write(0, "B1")
    #If a large number of row have been written, to avoid memory consumption flushes the rows
    #flushed row cannot be accessed or modified. Reccomended every 1000 rows
    ws.flush_row_data()
    row = ws.row(13)
    row.write(0, 'A2')
    row.write(1, 'B2')



    ws = wb.add_sheet('Sheet 2')


    #save at path
    wb.save('example.xls')

if __name__ == '__main__':
    main()