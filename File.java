public class File {

    private String fileName;
    private String fileType;

    //get set
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        this.fileName = fileName.trim();
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        if (fileType == null || fileType.isEmpty()) {
            throw new IllegalArgumentException("File type cannot be null or empty");
        }
        this.fileType = fileType.trim();
    }
    //

    public File(String fileName, String fileType) {
        setFileName(fileName);
        setFileType(fileType);
    }

    @Override
    public String toString() {
        return "File Name: " + fileName + "\nFile Type: " + fileType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof File)) return false;
        File file = (File) obj;
        return fileName.equalsIgnoreCase(file.fileName) &&
        fileType.equalsIgnoreCase(file.fileType);
    }

    // @Override
    // public int hashCode() {
    //     return fileName.toLowerCase().hashCode() + fileType.toLowerCase().hashCode();
    // }
}
