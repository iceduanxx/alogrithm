package cn.cb.internet;
import java.io.IOException;
import java.io.PrintWriter;

public interface PrintWriterCallback2
{

	public abstract Object output(PrintWriter printwriter)
		throws IOException;
}