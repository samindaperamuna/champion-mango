package cc.mobireach.api.util;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class ImageUtil {

	private static final String GENERIC_USER_IMAGE_URL = "user-generic.png";
	private static final String GENERIC_USER_IMAGE_TYPE = "png";
	private static final Dimension GENERIC_USER_IMAGE_SIZE = new Dimension(250, 250);

	/**
	 * Get the Base64 version of an image.
	 * 
	 * @param blob
	 * @return
	 */
	public static String getImageBase64(byte[] byteImage) {
		String encodedImage = null;

		try {
			byte[] base64Buffer = Base64.getEncoder().encode(byteImage);
			encodedImage = new String(base64Buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encodedImage;
	}

	/**
	 * Get the generic user image in the Base64 format.
	 * 
	 * @return
	 */
	public static String getGenericUserImageBase64() {
		String encodedImage = null;

		try {
			String fileName = ImageUtil.class.getClassLoader().getResource(GENERIC_USER_IMAGE_URL).getPath();
			File image = new File(fileName);
			BufferedImage bufferedImage = ImageIO.read(image);

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			ImageIO.write(scaleImage(bufferedImage, GENERIC_USER_IMAGE_SIZE), GENERIC_USER_IMAGE_TYPE, out);

			byte[] buffer = out.toByteArray();
			byte[] base64Buffer = Base64.getEncoder().encode(buffer);
			encodedImage = new String(base64Buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encodedImage;
	}

	/**
	 * Get blob from the Base64 image.
	 * 
	 * @param base64
	 * @return
	 */
	public static byte[] getByteImage(String base64) {
		byte[] buffer = null;

		try {
			buffer = Base64.getDecoder().decode(base64);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer;
	}

	/**
	 * Get a scaled buffered image.
	 * 
	 * @param image
	 * @param rescaleSize
	 * @return
	 */
	public static BufferedImage scaleImage(BufferedImage image, Dimension rescaleSize) {
		BufferedImage newImage;
		int width = (int) Math.round(rescaleSize.getWidth());
		int height = (int) Math.round(rescaleSize.getHeight());

		newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return newImage;
	}
}
