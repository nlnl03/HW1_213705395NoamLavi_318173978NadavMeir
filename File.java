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
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        if (fileType == null || fileType.isEmpty()) {
            throw new IllegalArgumentException("File type cannot be null or empty");
        }
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



}
