package br.com.elevedhl.compresspdf;


import java.io.IOException;

/**
 *
 * @author leonardo
 */
public class Compress {

    private Runtime run = Runtime.getRuntime();
    private Process process = null;
    private String pathPdfIn = null;
    private String pathPdfOut = null;
    private String namePdf = null;
    private String pathOut = null;
    private String pathIn = null;
    private String quality = null;

    public Compress(String pathPdfIn, String pathPdfOut, String namePdf, String quality) {
        this.namePdf = namePdf;
        this.pathPdfIn = pathPdfIn;
        this.pathPdfOut = pathPdfOut;
        this.quality = quality;
        pathIn();
        pathOut();
    }

    public void compress() throws IOException, InterruptedException {

        String so = System.getProperty("os.name");
        so = so.toLowerCase();
        if (so.contains("linux")) {
            process = run.exec("gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.3 -dPDFSETTINGS=/" + this.quality + " -dNOPAUSE -dBATCH -sOutputFile=" + this.pathOut + " " + this.pathIn);
            process.waitFor();
        } else if (so.contains("windows")) {
            process = run.exec("C:\\Program Files (x86)\\gs\\gs9.19\\bin\\gswin32c.exe -sDEVICE=pdfwrite -dCompatibilityLevel=1.3 -dPDFSETTINGS=/" + this.quality + " -dNOPAUSE -dBATCH -o " + this.pathOut + " " + this.pathIn);
            process.waitFor();
        }
    }

    private void pathOut() {
        String nameOut = this.namePdf.replace(".pdf", "");
        this.pathOut = this.pathPdfOut.replace(this.namePdf, nameOut + "_compactado.pdf");
    }

    private void pathIn() {
        this.pathIn = this.pathPdfIn.replace(" ", "\\ ");
    }

}
