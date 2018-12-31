package de.hegmanns.adventofcode2018.common.streamprovider;

import java.io.InputStream;

/**
 * Special {@link InputStreamProvider}-Implementation with opens a file in the same package of a given class and a spezified name.
 * 
 * @author B. Hegmanns
 *
 */
public class PackageFileInputStreamProvider implements InputStreamProvider {

	private Class<?> clazz;
	private String filename;
	public PackageFileInputStreamProvider(Class<?> clazz, String filename) {
		this.clazz = clazz;
		this.filename = filename;
	}



	public static InputStreamProvider get(Class<?> clazz, String filename) {
		return new PackageFileInputStreamProvider(clazz, filename);
	}
	
	
	
	@Override
	public InputStream getInputStream() {
		String basePath = clazz.getPackage().getName().replace('.', '/');
		String completePath = basePath + "/" + filename;
		InputStream inputStream = clazz.getClassLoader().getResourceAsStream(completePath);
		
		return inputStream;
	}


}
