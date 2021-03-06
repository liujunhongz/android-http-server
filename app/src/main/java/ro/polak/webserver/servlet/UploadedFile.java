/**************************************************
 * Android Web Server
 * Based on JavaLittleWebServer (2008)
 * <p/>
 * Copyright (c) Piotr Polak 2008-2015
 **************************************************/

package ro.polak.webserver.servlet;

import java.io.File;

import ro.polak.webserver.MultipartHeaders;

/**
 * Uploaded file representation
 *
 * @author Piotr Polak piotr [at] polak [dot] ro
 * @version 201509
 * @since 200802
 */
public class UploadedFile {

    private String name;
    private String fileName;
    private File file;
    private boolean isMoved = false;

    /**
     * Constructs uploaded file
     *
     * @param h    multipart headers
     * @param file uploaded file (temp)
     */
    public UploadedFile(MultipartHeaders h, File file) {
        this.name = h.getPostFieldName();
        this.fileName = h.getFileName();
        this.file = file;
    }

    /**
     * Moves the uploaded file to the specified destination
     *
     * @param path file destination
     * @return true if file moved
     */
    public boolean moveTo(String path) {
        File dest = new File(path);
        isMoved = file.renameTo(dest);
        return isMoved;
    }

    /**
     * Moves the uploaded file to the specified destination
     *
     * @param dest file destination
     * @return true if file moved
     */
    public boolean moveTo(File dest) {
        isMoved = file.renameTo(dest);
        return isMoved;
    }

    /**
     * Deletes temporary file if unused
     *
     * @return true if deleted
     */
    public boolean destroy() {
        if (isMoved) {
            return false;
        }
        return file.delete();
    }

    /**
     * Returns the HTML form name
     *
     * @return the HTML form name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the name of uploaded file
     *
     * @return the name of uploaded file
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Returns the size (length) of uploaded file in bytes
     *
     * @return the size (length) of uploaded file in bytes
     */
    public long size() {
        return file.length();
    }
}
