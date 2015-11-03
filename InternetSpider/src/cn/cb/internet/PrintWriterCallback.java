package cn.cb.internet;
import java.io.IOException;
import java.io.PrintWriter;

public interface PrintWriterCallback
{

	public abstract void output(PrintWriter printwriter)
		throws IOException;
}