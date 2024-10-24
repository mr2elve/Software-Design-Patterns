import java.util.ArrayList;
import java.util.List;

public class DocumentVersionControlSystem {

    class Document {
        private String content;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public DocumentVersion save() {
            return new DocumentVersion(content);
        }

        public void restore(DocumentVersion version) {
            this.content = version.getContent();
        }
    }

    class DocumentVersion {
        private String content;

        public DocumentVersion(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    class VersionControl {
        private List<DocumentVersion> versions = new ArrayList<>();

        public void saveVersion(Document document) {
            versions.add(document.save());
        }

        public void showVersions() {
            for (int i = 0; i < versions.size(); i++) {
                System.out.println("Version " + (i + 1) + ": " + versions.get(i).getContent());
            }
        }

        public void restoreVersion(Document document, int index) {
            if (index >= 0 && index < versions.size()) {
                document.restore(versions.get(index));
            } else {
                System.out.println("Invalid version index");
            }
        }
    }

    public static void main(String[] args) {
        DocumentVersionControlSystem system = new DocumentVersionControlSystem();
        Document document = system.new Document();
        VersionControl versionControl = system.new VersionControl();

        document.setContent("Version 1: Initial Content");
        versionControl.saveVersion(document);

        document.setContent("Version 2: Updated Content");
        versionControl.saveVersion(document);

        document.setContent("Version 3: Final Content");
        versionControl.saveVersion(document);

        System.out.println("Current Document Content: " + document.getContent());
        versionControl.showVersions();

        versionControl.restoreVersion(document, 1);
        System.out.println("Restored Document Content: " + document.getContent());
    }
}
