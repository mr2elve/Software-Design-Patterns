public class ReportGenerationSystem {

    abstract class ReportGenerator {
        public final void generateReport() {
            formatHeader();
            formatBody();
            formatFooter();
        }

        protected abstract void formatHeader();
        protected abstract void formatBody();
        protected abstract void formatFooter();
    }

    class PDFReportGenerator extends ReportGenerator {
        @Override
        protected void formatHeader() {
            System.out.println("PDF Header");
        }

        @Override
        protected void formatBody() {
            System.out.println("PDF Body");
        }

        @Override
        protected void formatFooter() {
            System.out.println("PDF Footer");
        }
    }

    class HTMLReportGenerator extends ReportGenerator {
        @Override
        protected void formatHeader() {
            System.out.println("HTML Header");
        }

        @Override
        protected void formatBody() {
            System.out.println("HTML Body");
        }

        @Override
        protected void formatFooter() {
            System.out.println("HTML Footer");
        }
    }

    class PlainTextReportGenerator extends ReportGenerator {
        @Override
        protected void formatHeader() {
            System.out.println("Plain Text Header");
        }

        @Override
        protected void formatBody() {
            System.out.println("Plain Text Body");
        }

        @Override
        protected void formatFooter() {
            System.out.println("Plain Text Footer");
        }
    }

    public static void main(String[] args) {
        ReportGenerationSystem system = new ReportGenerationSystem();

        ReportGenerator pdfReport = system.new PDFReportGenerator();
        pdfReport.generateReport();

        System.out.println();

        ReportGenerator htmlReport = system.new HTMLReportGenerator();
        htmlReport.generateReport();

        System.out.println();

        ReportGenerator plainTextReport = system.new PlainTextReportGenerator();
        plainTextReport.generateReport();
    }
}
