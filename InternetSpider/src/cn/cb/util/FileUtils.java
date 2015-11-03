package cn.cb.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtils {

	private static Log log = LogFactory.getLog(FileUtils.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param file
	 * @param charsetName
	 * @param append
	 * @param callback
	 * @return
	 */
	public static Object saveFile(File file, String charsetName,
			boolean append, PrintWriterCallback2 callback) {
		PrintWriter out = null;
		if (StringUtils.isNotEmpty(charsetName)) {
			charsetName = "utf-8";
			log.warn("charsetName is null, default utf-8 used.");
		}

		Object obj = null;
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append), charsetName));
			out = new PrintWriter(writer, true);

			obj = callback.output(out);
		} catch (IOException e) {
			log.error(e);
		}
		if (out != null) {
			out.close();
		}

		return obj;

	}

	public static void saveFile(File file, String charsetName, boolean append,
			PrintWriterCallback callback) {
		PrintWriter out;
		if (StringUtils.isEmpty(charsetName)) {
			charsetName = "utf-8";
			log.warn("charsetName is null, default utf-8 used.");
		}
		out = null;
		BufferedWriter writer;
		try {

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append), charsetName));
			out = new PrintWriter(writer, true);
			callback.output(out);

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	public static void saveFile(File file, String charsetName, boolean append,
			OutPutStreamCallback callback) {
		if (StringUtils.isEmpty(charsetName)) {
			charsetName = "utf-8";
			log.warn("charsetName is null, default utf-8 used.");
		}

		BufferedOutputStream out = null;
		try {

			out = new BufferedOutputStream(new FileOutputStream(file, append));

			callback.output(out);

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
