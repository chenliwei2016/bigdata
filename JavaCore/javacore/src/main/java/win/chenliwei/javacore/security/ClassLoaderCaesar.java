/**
 * @author: ChenLiwei
 * 2017-03-21
 * ClassLoaderCaesar.java
 * Comments: It is a tool to decrypt and run the class which was encrypted by Caesar class
 * For example, we encrypted a class before for win.chenliwei.javacore.basic.SwitchCase
 *  and the encryption key is 3, so run this by adding 2 parameters with 3 and win.chenliwei.javacore.basic.SwitchCase
 */
package win.chenliwei.javacore.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassLoaderCaesar extends ClassLoader{
	private int key;

	public ClassLoaderCaesar(int key) {
		this.key = key;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ClassLoaderCaesar classloader = new ClassLoaderCaesar(Integer.parseInt(args[0]));
		Class<?> c = classloader.loadClass(args[1]);
		Method m = c.getMethod("main", String[].class);
		m.invoke(null, (Object)new String[]{});
	}
	
	
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = null;
		try {
			classBytes = loadClassBytes(name);
			Class<?> returnClass = defineClass(name, classBytes, 0, classBytes.length);
			if(returnClass == null) throw new ClassNotFoundException(name);
			return returnClass;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClassNotFoundException(name);
		}
	}



	private byte[] loadClassBytes(String name) throws IOException{
		String[] paths = System.getProperty("java.class.path").replace("\\", "/").split(";");
		String cname = name.replace(".", "/") + ".caesar";
		for(String path : paths){
			if(Paths.get(path + "/" + cname) != null){
				cname = path + "/" + cname;
				break;
			}
		}
		byte[] bytes;
		bytes = Files.readAllBytes(Paths.get(cname));
		for(int i=0 ; i < bytes.length; i++) bytes[i] = (byte)(bytes[i] - key);
		return bytes;
	}

}
