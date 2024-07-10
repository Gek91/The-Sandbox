import xlsxwriter

workbook = xlsxwriter.Workbook('test.xlsx')


worksheet = workbook.add_worksheet("my-worksheet") #specify name

worksheet.write("A1", "hello world")

expenses = (
    ["rent", 1000],
    ["gas", 100],
    ["food", 300],
    ["gym", 50],
)

row = 3
col = 0

for item, cost in (expenses) :
    #write(row, col, value)
    worksheet.write(row, col, item)
    worksheet.write(row, col +1, cost)
    row += 1

#total with formula
worksheet.write(row, col, "total")
worksheet.write(row, col +1, "=SUM(B4:B7)")

#chart
data = [
    [1,2,3,4,5],
    [2,4,6,8,10],
    [3,6,9,12,15]
]

worksheet.write_column("G1", data[0])
worksheet.write_column("H1", data[1])
worksheet.write_column("I1", data[2])


chart = workbook.add_chart({"type": "pie", "name": "my-chart"})
chart.add_series({"values": "=my-worksheet!$G$1:$G$5"})
chart.add_series({"values": "=my-worksheet!$H$1:$H$5"})
chart.add_series({"values": "=my-worksheet!$I$1:$I$5"})
worksheet.insert_chart("E3", chart)

workbook.close()
