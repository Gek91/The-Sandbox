import java.awt.Color;
	import java.io.ByteArrayInputStream;
	import java.io.ByteArrayOutputStream;
	import java.io.File;
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.URISyntaxException;

	import org.apache.pdfbox.pdmodel.PDDocument;
	import org.apache.pdfbox.pdmodel.PDPage;
	import org.apache.pdfbox.pdmodel.PDPageContentStream;
	import org.apache.pdfbox.pdmodel.common.PDRectangle;
	import org.apache.pdfbox.pdmodel.font.PDType1Font;
	import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
	import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


	/**
	 * Utils class for PDF programmatic generation
	 * Based on Apache PDFBox library 
	 * 
	 * @author Giacomo Pandini
	 *
	 */

	public class PdfDocument implements AutoCloseable{

		private static final float MARGIN_X = 10f;
		private static final float MARGIN_Y = 10f;
		private static final float SIZE_X = PDRectangle.A4.getHeight();
		private static final float SIZE_Y = PDRectangle.A4.getWidth();
		
		private static final float HEADER_X = 300f;
		private static final float HEADER_Y = 100f;
		private static final float HEADER_FONT_SIZE = 10f;
		
		private static final float FIELD_BAR_Y = 20f;
		private static final float STATUS_FIELD_X = 100f;
		private static final float SENDER_FIELD_X = 200f;
		private static final float SUBJECT_FIELD_X = 300f;
		
		private static final float CONTENT_CELL_Y = 40f;
		
		private static final float WIDTH;
		private static final float HEADER_ROW_Y;
		
		static {
			WIDTH = SIZE_X - MARGIN_X * 2;
			HEADER_ROW_Y = HEADER_Y / 4;
		}
		
		private PDDocument document;
		private PDRectangle mediaboxSize;
				
		private PDPage lastPage;
		private float lastLinePosition;
				
		private PDPageContentStream contentStream;
		
		private String reportDate;
			
		//////////////////////////////////////////////////////////////////////////////Constructors////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Constructor with setting parameters
		 * 
		 *  @throws IOException 
		 */
		public PdfDocument(String reportDate) throws IOException {
			
			this.document = new PDDocument();
//			this.mediaboxSize = PDRectangle.A4;
			this.mediaboxSize = new PDRectangle(SIZE_X, SIZE_Y);
						
			this.reportDate = reportDate;
			
			//Set first page and lastLinePosition, open contentStream
			moveToANewPage();	
		}
		
		
		@Override
		public void close() throws Exception {
					
			closeContentStream();
			
			this.document.close();
		}
		
		///////////////////////////////////////////////////////////////////////////////Public methods////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Save document with given name
		 * @throws Exception 
		 */
		public void saveDocument(String docName) throws Exception {
			
			if(this.document.getDocument().isClosed()) {
				throw new Exception("Closed");
			}
			
			addPageFooter();
			
			closeContentStream();
			
			this.document.save(docName);		
		}
		
		/**
		 * Return document as inputStream
		 * 
		 * @return
		 * @throws Exception 
		 */
		public InputStream getDocumentInputStream() throws Exception {
			
			if(this.document.getDocument().isClosed()) {
				throw new Exception("Closed"); 
			}
			
			addPageFooter();
			
			closeContentStream();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			this.document.save(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
			
		public void createHeader(String logoPath, String mailbox, String reportDate, String reportLog) throws IOException, URISyntaxException {
			
			File file = new File(PdfDocument.class.getClassLoader().getResource(logoPath).toURI());
			
			//Immagine parte sinistra
			PDImageXObject img = PDImageXObject.createFromFileByContent(file, this.document);
			contentStream.drawImage(img, MARGIN_X + 20, lastLinePosition - 100 + 15, 238, 70);
					
			//Rettangolo Header
			contentStream.moveTo(MARGIN_X, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition - HEADER_Y);
			this.contentStream.lineTo(MARGIN_X, this.lastLinePosition - HEADER_Y);
			this.contentStream.lineTo(MARGIN_X, this.lastLinePosition);
			this.contentStream.moveTo(MARGIN_X + HEADER_X, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X + HEADER_X, this.lastLinePosition - HEADER_Y);
			this.contentStream.stroke();
			
			//Struttura parte destra
			this.contentStream.setNonStrokingColor(Color.BLACK);
			this.contentStream.addRect(MARGIN_X + HEADER_X, this.lastLinePosition - HEADER_ROW_Y, WIDTH - HEADER_X, HEADER_ROW_Y);
			this.contentStream.fill();
			
			this.contentStream.moveTo(MARGIN_X + HEADER_X, this.lastLinePosition - HEADER_ROW_Y * 2);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition - HEADER_ROW_Y * 2);	
			this.contentStream.moveTo(MARGIN_X + HEADER_X, this.lastLinePosition - HEADER_ROW_Y * 3);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition - HEADER_ROW_Y *3);
			this.contentStream.stroke();

			//Contenuto parte destra
			this.contentStream.setNonStrokingColor(Color.WHITE); 
			this.contentStream.beginText();
			this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, HEADER_FONT_SIZE);
			this.contentStream.newLineAtOffset(MARGIN_X + HEADER_X + 5, this.lastLinePosition - 16);
			this.contentStream.showText("CORPORATE PHARMACOVIGILANCE DAILY REPORT");
			this.contentStream.endText();
			
			this.contentStream.setNonStrokingColor(Color.BLACK); 
			this.contentStream.beginText();
			this.contentStream.newLineAtOffset(MARGIN_X + HEADER_X + 5, this.lastLinePosition - 40);
			this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, HEADER_FONT_SIZE);
			this.contentStream.showText("Mailbox: ");
			this.contentStream.setFont(PDType1Font.HELVETICA, HEADER_FONT_SIZE);
			this.contentStream.showText(mailbox);
			this.contentStream.newLineAtOffset(0 , -HEADER_ROW_Y);
			this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, HEADER_FONT_SIZE);
			this.contentStream.showText("Date: ");
			this.contentStream.setFont(PDType1Font.HELVETICA, HEADER_FONT_SIZE);
			this.contentStream.showText(reportDate);
			this.contentStream.newLineAtOffset(0 , -HEADER_ROW_Y);
			this.contentStream.setFont(PDType1Font.HELVETICA_BOLD, HEADER_FONT_SIZE);
			this.contentStream.showText("Report log: ");
			this.contentStream.setFont(PDType1Font.HELVETICA, HEADER_FONT_SIZE);
			this.contentStream.showText(reportLog);
			this.contentStream.endText();
			
			this.lastLinePosition = this.lastLinePosition - HEADER_Y - 25;			
		}
		
		public void tableFieldBar() throws IOException {
			
			this.contentStream.setNonStrokingColor(Color.BLACK);
			this.contentStream.addRect(MARGIN_X - 0.5f, this.lastLinePosition, STATUS_FIELD_X + 1, FIELD_BAR_Y);
			this.contentStream.addRect(MARGIN_X + STATUS_FIELD_X + 1, this.lastLinePosition, SENDER_FIELD_X - 0.5f, FIELD_BAR_Y);
			this.contentStream.addRect(MARGIN_X + SUBJECT_FIELD_X + 1, this.lastLinePosition,  WIDTH - SUBJECT_FIELD_X - 0.5f, FIELD_BAR_Y);
			this.contentStream.fill();

			this.contentStream.setNonStrokingColor(Color.WHITE);
			this.contentStream.beginText();
			this.contentStream.setFont(PDType1Font.HELVETICA, HEADER_FONT_SIZE);
			
			this.contentStream.newLineAtOffset(MARGIN_X + 35, this.lastLinePosition + 6);
			this.contentStream.showText("Status");
			this.contentStream.newLineAtOffset(STATUS_FIELD_X + 35, 0);
			this.contentStream.showText("Sender/Time");
			this.contentStream.newLineAtOffset(SENDER_FIELD_X + 150, 0);
			this.contentStream.showText("Subject");
			this.contentStream.endText();
			
		}
		
		public void tableRow(boolean found, String sender, String sendDate, String subject, String emailId, boolean even) throws IOException {
			
			checkEndOfPageAndAddPageNumber();
			
			//Evidenziazione not found		
			if(!found) {
				
				if(even) {
					this.contentStream.setNonStrokingColor(255, 153, 0);
				} else {
					this.contentStream.setNonStrokingColor(255, 194, 104);
				}
				
			} else {
				
				if(even) {
					this.contentStream.setNonStrokingColor(230, 230, 230);
				} else {
					this.contentStream.setNonStrokingColor(255, 255, 255);
				}
			}
			
			this.contentStream.addRect(MARGIN_X + 0.5f, this.lastLinePosition - CONTENT_CELL_Y, STATUS_FIELD_X - 0.5f, CONTENT_CELL_Y - 0.5f);
			this.contentStream.fill();
		
			
			if(!found) {
				
				if(even) {
					this.contentStream.setNonStrokingColor(239, 174, 174);
				} else {
					this.contentStream.setNonStrokingColor(244, 204, 204);
				}
				
			} else {
				
				if(even) {
					this.contentStream.setNonStrokingColor(230, 230, 230);
				} else {
					this.contentStream.setNonStrokingColor(255, 255, 255);
				}
			}
			this.contentStream.addRect(MARGIN_X + STATUS_FIELD_X + 2, this.lastLinePosition - CONTENT_CELL_Y, WIDTH - STATUS_FIELD_X - 2.5f, CONTENT_CELL_Y - 0.5f);
			this.contentStream.fill();
			//
			
			
			
			//Celle riga
			this.contentStream.moveTo(MARGIN_X, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X, this.lastLinePosition - CONTENT_CELL_Y);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition - CONTENT_CELL_Y);
			this.contentStream.lineTo(MARGIN_X + WIDTH, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X, this.lastLinePosition);
			
			this.contentStream.moveTo(MARGIN_X + STATUS_FIELD_X + 1, this.lastLinePosition);
			this.contentStream.lineTo(MARGIN_X + STATUS_FIELD_X + 1, this.lastLinePosition - CONTENT_CELL_Y);
		
			this.contentStream.stroke();
			
			//Contenuto riga
			
			PDType1Font font = found ? PDType1Font.HELVETICA : PDType1Font.HELVETICA_BOLD;
			
			this.contentStream.setNonStrokingColor(Color.BLACK); 
			this.contentStream.beginText();
			this.contentStream.setFont(font, HEADER_FONT_SIZE);
			this.contentStream.newLineAtOffset(MARGIN_X + 5, this.lastLinePosition - CONTENT_CELL_Y / 2 - 3);
			this.contentStream.showText(found ? "Found" : "Not Found");			
			this.contentStream.newLineAtOffset(STATUS_FIELD_X, 7);
			this.contentStream.showText(manageFieldStringLenght(sender, SENDER_FIELD_X - 5, font, HEADER_FONT_SIZE));
			this.contentStream.newLineAtOffset(SENDER_FIELD_X, 0);
			this.contentStream.showText(manageFieldStringLenght(sanitizeSubjectString(subject), WIDTH - HEADER_X - 5, font, HEADER_FONT_SIZE));
			this.contentStream.newLineAtOffset(-SENDER_FIELD_X, - 17);
			this.contentStream.showText(sendDate);
			this.contentStream.newLineAtOffset(SENDER_FIELD_X, 0);
			this.contentStream.setFont(font, HEADER_FONT_SIZE -3);
			this.contentStream.showText(emailId);
			this.contentStream.endText();
			
			this.lastLinePosition = this.lastLinePosition - CONTENT_CELL_Y;

		}
			
		///////////////////////////////////////////////////////////////////////////////////Private methods////////////////////////////////////////////////////////////////////////////
		
		/*
		 * Content Stream Management
		 */
		private void openContentStream() throws IOException {
			this.contentStream = new PDPageContentStream(PdfDocument.this.document, PdfDocument.this.lastPage, PDPageContentStream.AppendMode.APPEND, true, true);
		}
		
		private void closeContentStream() throws IOException {
			this.contentStream.close();
		}
		
		private void moveToANewPage() throws IOException {
			
			PDPage page = this.lastPage = new PDPage(this.mediaboxSize);
			this.document.addPage(page);
			this.lastPage = page;
			this.lastLinePosition = this.mediaboxSize.getHeight() - MARGIN_Y ;
			
			if(contentStream != null) {
				contentStream.close();
			}
			
			openContentStream();
		}
		
		/*
		 * Text management
		 */
		private String manageFieldStringLenght(String field, float maxLenght, PDType1Font font, float fontSize) throws IOException {
		
			String result = field;
					
			float lineSize = fontSize * font.getStringWidth(field) / 1000;
		
			int i = field.length()-3;
			while(lineSize > maxLenght) {
				
				result = field.substring(0, i) + "..";
				
				lineSize = fontSize * font.getStringWidth(result) / 1000;
				i--;
			}
			
			return result;
		}

		private void checkEndOfPageAndAddPageNumber() throws IOException {
			
			if(this.lastLinePosition - CONTENT_CELL_Y < MARGIN_Y + HEADER_FONT_SIZE) { //2 righe
				
				addPageFooter();
				
				moveToANewPage();
				this.lastLinePosition = this.lastLinePosition - FIELD_BAR_Y;
				tableFieldBar();
			} 
		}
		
		private void addPageFooter() throws IOException {
			
			this.contentStream.beginText();
			this.contentStream.newLineAtOffset(380, 5);
			this.contentStream.showText(reportDate + " | " + this.document.getPages().getCount());
			this.contentStream.endText();
		}
		
		private String sanitizeSubjectString(String subject) {
			
			//Manage no break space character
			subject = subject.replace('\u00A0',' ').trim();
			
	        StringBuilder sanitizedString = new StringBuilder();
			
			 for (int i = 0; i < subject.length(); i++) {
		            if (WinAnsiEncoding.INSTANCE.contains(subject.charAt(i))) {
		            	
		            	sanitizedString.append(subject.charAt(i));
		            } else {
		            	sanitizedString.append("_");
		            }
		        }
		        return sanitizedString.toString();
		}
		
		////////////////////////////////////////////////////////////////////////////////////Main///////////////////////////////////////////////////////////////////////////////
		
		//MAIN TEST
		public static void main(String[] args) {
		
			try(PdfDocument doc = new PdfDocument("05/05/2018")){
				
				doc.createHeader("img/logo.jpg", "lamiamail@menarini.it", "May 18 2018", "");
				
				doc.tableFieldBar();

				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Out of office \uD83C\uDF34 Re: MEN1611-01 - SUSAR Reconciliation - Minor Corrections ICSR BE-MEN-074350(1) in ARISg", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",true);

//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
//
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
//				
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
//			
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
//	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
//				
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
//			
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
//				
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
//			
//				doc.tableRow(true, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);
	//
//				doc.tableRow(true, "aaaaaaaaaaaa11111111aamiamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LPaaaaaaaaaaaa333333333333333333333333333333333333333333333333aaaaaaa111111333333333333333333333334", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com", true);
	//
//				doc.tableRow(false, "miamail@menarini.it", "05/05/2018", "Query: Pending E2B Acks from Menarini - LP", "c6a6d2b25d2e49ff871e84a33449d762@FCEXC13PRDN07.na.gilead.com",false);

				
				System.out.println(doc.mediaboxSize.getHeight());
				System.out.println(doc.mediaboxSize.getWidth());
							
				doc.saveDocument("my_doc.pdf");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}


