/*
 * Copyright (C) 2014 Nathan Emery
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jsplitserver;
import java.io.*;

/**
 *
 * @author Nathan
 */
public class FileSplitter {
    public static void split(File f, int chunkSize) throws IOException {
        BufferedInputStream biStream;
        biStream = new BufferedInputStream(new FileInputStream(f));
        FileOutputStream out;
        String fileName = f.getName();
        int chunkCount  = 1;
        byte[] inputBuffer = new byte[chunkSize];
        int bytesRead = 0;
        while ((bytesRead = biStream.read(inputBuffer)) > 0) {
            File newFile = new File(f.getParent() + "\\" +
                    fileName + "." + String.format("%03d", chunkCount++));
            newFile.createNewFile();
            out = new FileOutputStream(newFile);
            out.write(inputBuffer, 0, bytesRead);
            out.close();
        }
    }
}
