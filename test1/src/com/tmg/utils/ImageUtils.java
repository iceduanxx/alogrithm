package com.tmg.utils;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ͼƬ��ˮӡ���Զ����ŵ��࣬������ҪImageMagick��JImageMaigick���ʹ��<br>
 * ����CMSר�� <br>
 * ʹ�ø���ע������ <br>
 * �ر�ע�⣺ <br>
 * 1���������ϰ�װImageMagick <br>
 * ���ص�ַ��Linux: ftp://ftp.imagemagick.org/pub/ImageMagick/ImageMagick.tar.gz <br>
 * Windows��ftp://ftp.imagemagick.org/pub/ImageMagick/binaries/ImageMagick-6.2.6-8-Q16-windows-dll.exe
 * <br>
 * 2���������ϰ�װ��JMagick (PATH=/home5/jdk1.5.0_07/bin:$PATH) <br>
 * ���ص�ַ��Linux��http://www.yeo.id.au/jmagick/quickload/JMagick-6.2.6-0.tar.gz <br>
 * Windows��http://www.yeo.id.au/jmagick/quickload/win-6.2.6/jmagick-6.2.6-win.zip
 * <br>
 * winodws ��ʹ��6.3.9�汾�� 3�����û������� <br>
 * �޸�myconfig.sh <br>
 * �� JAVA_OPTS="-server";export JAVA_OPTS <br>
 * ���� JAVA_OPTS="-Djava.awt.headless=true -Djava.library.path=/usr/local/lib
 * -Djmagick.systemclassloader=no -server";export JAVA_OPTS <br>
 * 
 * @author Yangsy <br>
 * @author garmbrood Date: 2006-4-25 10:14:37 <br>
 */
public class ImageUtils {

	public static final int POS_TYPE_LEFT_TOP = 1;
	public static final int POS_TYPE_RIGHT_TOP = 2;
	public static final int POS_TYPE_LEFT_BOTTOM = 3;
	public static final int POS_TYPE_RIGHT_BOTTOM = 4;
	public static final int POS_TYPE_CENTER = 5;

    public static final Map<String ,MagickImage> logoCache = new HashMap<String, MagickImage>();

	static {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * ��ÿ��
	 * 
	 * @param picFrom
	 *            ͼƬ��ַ
	 * @return ���ؿ�
	 */
	public static String getWidthHeight(String picFrom) throws MagickException {
		MagickImage image = new MagickImage(new ImageInfo(picFrom));

		Dimension dimension = image.getDimension();
		int iWi = (int) dimension.getWidth();
		int iHi = (int) dimension.getHeight();
		return iWi + "," + iHi;
	}

	/**
	 * ��ø�
	 * 
	 * @param picFrom
	 *            ͼƬ��ַ
	 * @return ���ظ�
	 */
	public static int getWidth(String picFrom) throws MagickException {
		MagickImage image = new MagickImage(new ImageInfo(picFrom));

		Dimension dimension = image.getDimension();
		int iWi = (int) dimension.getWidth();
		return iWi;
	}

	/**
	 * ��ÿ�
	 * 
	 * @param picFrom
	 *            ͼƬ��ַ
	 * @return ���ؿ���ַ���
	 */
	public static int getHeight(String picFrom) throws MagickException {
		MagickImage image = new MagickImage(new ImageInfo(picFrom));

		Dimension dimension = image.getDimension();
		int iHi = (int) dimension.getHeight();
		return iHi;
	}

	/**
	 * ����ͼƬ
	 * 
	 * @param picFrom
	 *            �����ˮӡ��ͼƬ��ַ
	 * @param picTo
	 *            ���ź��ͼƬ��ַ
	 * @param width
	 *            ���ź�Ŀ��
	 * @param height
	 *            ���ź�ĸ߶�
	 * @param scale
	 *            �ǵȱ����ţ�����ǵȱ����ţ������� width�� height��ʾ��Ŀ�Ⱥ͸߶�
	 * @return �Ƿ����ųɹ�
	 */
	public static boolean resize(String picFrom, String picTo, int width,
			int height, boolean scale) {

        try {
            width = width==0?500:width;
            // Resize
            ImageInfo info = new ImageInfo(picFrom);
            MagickImage image = new MagickImage(new ImageInfo(picFrom));
            MagickImage scaled = null;// СͼƬ�ļ��Ĵ�С.

            Dimension dimension = image.getDimension();
            int iWi = (int) dimension.getWidth();
            int iHi = (int) dimension.getHeight();

            if((iWi <= width && width > 0)&&(iHi <= height && height > 0)){
                File srcFile = new File(picFrom);
                File destFile = new File(picTo);
                try {
                    FileUtils.copyFile(srcFile, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (!scale) {
                scaled = image.scaleImage(width, height);
            } else {
                int toWidth;
                int toHeight;
                if (width < 1) {
                    toWidth = (int) (((float) height / (float) iHi) * iWi);
                    toHeight = height;
                } else if (height < 1) {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                }  else {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                }
                image.profileImage("*", null);// �Ƴ�ͼƬ��������Ϣ
                scaled = image.scaleImage(toWidth, toHeight);
            }

            scaled.setFileName(picTo);
            info.setQuality(90);
            scaled.writeImage(info);
        } catch (MagickException e) {
            e.printStackTrace(); 
        }
        return true;
	}

    public static boolean resizeForSoft(String picFrom, String picTo, int width,
			int height, boolean scale) {

        try {
            width = width==0?500:width;
            // Resize
            ImageInfo info = new ImageInfo(picFrom);
            MagickImage image = new MagickImage(new ImageInfo(picFrom));
            MagickImage scaled = null;// СͼƬ�ļ��Ĵ�С.

            Dimension dimension = image.getDimension();
            int iWi = (int) dimension.getWidth();
            int iHi = (int) dimension.getHeight();

            if((iWi <= width && width > 0)&&(iHi <= height && height > 0)){
                File srcFile = new File(picFrom);
                File destFile = new File(picTo);
                try {
                    FileUtils.copyFile(srcFile, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (!scale) {
                scaled = image.scaleImage(width, height);
            } else {
                int toWidth;
                int toHeight;
                if (width < 1) {
                    toWidth = (int) (((float) height / (float) iHi) * iWi);
                    toHeight = height;
                } else if (height < 1) {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                } else if(((float) width / (float) height) > ((float) iWi / (float) iHi)){
                    toWidth = (int) (((float) height / (float) iHi) * iWi);
                    toHeight = height;
                }else {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                }
                image.profileImage("*", null);// �Ƴ�ͼƬ��������Ϣ
                scaled = image.scaleImage(toWidth, toHeight);
            }

            scaled.setFileName(picTo);
            info.setQuality(90);
            scaled.writeImage(info);
        } catch (MagickException e) {
            e.printStackTrace();
        }
        return true;
	}
      /**
       * ���ֻ��ͻ����ṩ��ͼƬ����
       * */
      public static boolean resizeForMoblie(String picFrom, String picTo, int width,
			int height, boolean scale) {

        try {
            width = width==0?500:width;
            // Resize
            ImageInfo info = new ImageInfo(picFrom);
            MagickImage image = new MagickImage(new ImageInfo(picFrom));
            MagickImage scaled = null;// СͼƬ�ļ��Ĵ�С.

            Dimension dimension = image.getDimension();
            int iWi = (int) dimension.getWidth();
            int iHi = (int) dimension.getHeight();

            if((iWi <= width && width > 0)&&(iHi <= height && height > 0)){
                File srcFile = new File(picFrom);
                File destFile = new File(picTo);
                try {
                    FileUtils.copyFile(srcFile, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (!scale) {
                scaled = image.scaleImage(width, height);
            } else {
                int toWidth;
                int toHeight;
                if (width < 1) {
                    toWidth = (int) (((float) height / (float) iHi) * iWi);
                    toHeight = height;
                } else if (height < 1) {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                } else if(((float) width / (float) height) > ((float) iWi / (float) iHi)){
                    toWidth = (int) (((float) height / (float) iHi) * iWi);
                    toHeight = height;
                }else {
                    toWidth = width;
                    toHeight = (int) (((float) width / (float) iWi) * iHi);
                }
                image.profileImage("*", null);// �Ƴ�ͼƬ��������Ϣ
                scaled = image.scaleImage(toWidth, toHeight);
            }

            scaled.setFileName(picTo);
            info.setQuality(90);
            scaled.writeImage(info);
        } catch (MagickException e) {
            e.printStackTrace();
        }
        return true;
	}
    /**
     * ˮӡ(ͼƬlogo)
     * @param filePath  Դ�ļ�·��
     * @param toImg     �޸�ͼ·��
     * @param logoPath  logoͼ·��
     * @throws magick.MagickException
     */
    public static void initLogoImg(String filePath, String toImg, String logoPath) throws MagickException {
        ImageInfo info = new ImageInfo();
        MagickImage fImage = null;
        MagickImage sImage = null;
        MagickImage fLogo = null;
        MagickImage sLogo = null;
        Dimension imageDim = null;
        Dimension logoDim = null;
        try {
            fImage = new MagickImage(new ImageInfo(filePath));
            imageDim = fImage.getDimension();
            int width = imageDim.width;
            int height = imageDim.height;
            if (width > 660) {
                height = 660 * height / width;
                width = 660;
            }
            sImage = fImage.scaleImage(width, height);
            fLogo = new MagickImage(new ImageInfo(logoPath));
            logoDim = fLogo.getDimension();
            int lw = width / 8;
            int lh = logoDim.height * lw / logoDim.width;
            sLogo = fLogo.scaleImage(lw, lh);
            sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo, width - (lw + lh / 10), height - (lh + lh / 10));
            sImage.setFileName(toImg);
            sImage.writeImage(info);
        } finally {
            if (sImage != null) {
                sImage.destroyImages();
            }
        }
    }

    /**
	 * ��ˮӡ������ˮӡ�ļ�ʹ��͸��png��ʽ
	 * 
	 * @param sourceFile
	 *            ����ˮӡ��ͼƬ
	 * @param watermark
	 *            ˮӡ�ļ�
	 * @param targetFile
	 *            ���ˮӡ����ļ����������ɵ��ļ�
	 * @param x
	 *            �߾�
	 * @param y
	 *            �߾�
	 * @param align
	 *            λ��,����μ� ImageUtils.POS_TYPE*
	 * @return �Ƿ���ӳɹ�
	 */
	public static boolean generateMark(String sourceFile, String watermark,
                                       String targetFile, int x, int y, int align) {
        ImageInfo info = null;
        MagickImage sourceImg = null;
        //MagickImage sImage = null;
        MagickImage fLogo = null;
        Dimension sourceDim = null;
        Dimension logoDim = null;
        try {
            info = new ImageInfo();
            sourceImg = new MagickImage(new ImageInfo(sourceFile));
            //Դͼ��Ϣ
            sourceDim = sourceImg.getDimension();
            //sImage = fImage.scaleImage(width, height);
            fLogo = new MagickImage(new ImageInfo(watermark));
            /*if(logoCache.containsKey(watermark)){
                fLogo = logoCache.get(watermark);
            }else{
                fLogo = new MagickImage(new ImageInfo(watermark));
                if(fLogo != null)
                    logoCache.put(watermark,fLogo);
            }*/
            System.out.println("watermark " + watermark);
            //ˮӡͼ��Ϣ
            logoDim = fLogo.getDimension();
            int lw =logoDim.width;
            int lh = logoDim.height;

            //��ͼ������С��6������ͼ��ȣ�����С����ͼ��С
            if(logoDim.width * 4 > sourceDim.width){
               lw = logoDim.width  * sourceDim.width  /(logoDim.width  * 4);
               lh = logoDim.height * lw / logoDim.width;
            }
            MagickImage sLogo = fLogo.scaleImage(lw,lh);

            sourceImg.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                    calcWatermarkPosX(sourceDim.width,sourceDim.height,lw,lh,x,y,align),
                    calcWatermarkPosY(sourceDim.width,sourceDim.height,lw,lh,x,y,align));
            sourceImg.setFileName(targetFile);
            sourceImg.writeImage(info);
        } catch (MagickException e) {
            e.printStackTrace();
        } finally {
            if (sourceImg != null) {
                sourceImg.destroyImages();
            }
            if(fLogo != null){
                fLogo.destroyImages();
            }
        }
        /* BufferedImage sourceImg = null;
      BufferedImage waterImg = null;
      try {
          sourceImg = ImageIO.read(new File(sourceFile));
          waterImg = ImageIO.read(new File(watermark));
      } catch (IOException e) {
          e.printStackTrace();
          System.out.println("ԭͼ��ˮӡͼƬ������");
          return false;
      }
      int sourceWidth = sourceImg.getWidth();
      int sourceHeight = sourceImg.getHeight();

      int waterImgWidth = waterImg.getWidth();
      int waterImgHeight = waterImg.getHeight();

      if (sourceWidth == -1 || waterImgWidth == -1){
              System.out.println("ԭͼ��ˮӡͼ���Ϊ0");
              return false;
      }

      BufferedImage bimage = new BufferedImage(sourceWidth, sourceHeight,
              BufferedImage.TYPE_INT_RGB);

      Graphics2D graphics2D = bimage.createGraphics();
      graphics2D.setColor(Color.red);
      graphics2D.setBackground(Color.white);
      graphics2D.drawImage(sourceImg, 0, 0, null);

      int waterX = calcWatermarkPosX(sourceWidth, sourceHeight,
              waterImgWidth, waterImgHeight, x, y, align);
      int waterY = calcWatermarkPosY(sourceWidth, sourceHeight,
              waterImgWidth, waterImgHeight, x, y, align);

      graphics2D.drawImage(waterImg, waterX, waterY, null);
      graphics2D.dispose();
      try {
          ImageIO.write(bimage,"jpg",new File(targetFile));
      } catch (IOException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      FileOutputStream out = null;
        try {
            out = new FileOutputStream(targetFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);

            param.setQuality(0.9F, true);
            encoder.encode(bimage, param);
        } catch (Exception e) {
            System.out.println("error at:" + e.getMessage());
            return false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/

		return true;
	}
   /**
     *
     * @param sourceImageWidth ԴͼƬ���
     * @param sourceImageHeight ԭͼƬ�߶�
     * @param waterImgWidth ˮӡͼ���
     * @param waterImgHeight ˮӡͼ�߶�
     * @param x xƫ����
     * @param y yƫ����
     * @param type λ��
     * @return x����
     */
	private static int calcWatermarkPosX(int sourceImageWidth,
			int sourceImageHeight, int waterImgWidth, int waterImgHeight,
			int x, int y, int type) {

		int result = 0;
		switch (type) {
		case POS_TYPE_LEFT_TOP:
		case POS_TYPE_LEFT_BOTTOM:
			result = x;
			break;
		case POS_TYPE_RIGHT_TOP:
		case POS_TYPE_RIGHT_BOTTOM:
			result = sourceImageWidth - (waterImgWidth + x);
			break;
		case POS_TYPE_CENTER:
			result = getCenter(sourceImageWidth, waterImgWidth);
			break;
		}

		result = (result >= 0 ? result : 0);
		return result;
	}

    /**
     *
     * @param sourceImageWidth ԴͼƬ���
     * @param sourceImageHeight ԭͼƬ�߶�
     * @param waterImgWidth ˮӡͼ���
     * @param waterImgHeight ˮӡͼ�߶�
     * @param x xƫ����
     * @param y yƫ����
     * @param type λ��
     * @return y����
     */
	private static int calcWatermarkPosY(int sourceImageWidth,
			int sourceImageHeight, int waterImgWidth, int waterImgHeight,
			int x, int y, int type) {

		int result = 0;
		switch (type) {
		case POS_TYPE_LEFT_TOP:
		case POS_TYPE_RIGHT_TOP:
			result = y;
			break;
		case POS_TYPE_LEFT_BOTTOM:
		case POS_TYPE_RIGHT_BOTTOM:
			result = sourceImageHeight - (waterImgHeight + y);
			break;
		case POS_TYPE_CENTER:
			result = getCenter(sourceImageHeight, waterImgHeight);
			break;
		}

		result = (result >= 0 ? result : 0);
		return result;
	}

	private static int getCenter(int sourceImage, int waterImage) {
		int h = 0;
		if (sourceImage * 3 / 5 + waterImage / 2 >= sourceImage)
			h = sourceImage - (10 + waterImage);
		else
			h = sourceImage * 3 / 5 - waterImage / 2;
		return h;
	}

	public static void main(String[] args) throws MagickException, IOException {
		System.setProperty("jmagick.systemclassloader", "no");
        System.out.println(System.getProperty("java.library.path"));


        String filepath  = "F:\\hzv\\hezw\\DSC03659.JPG";
        ImageUtils.resize(filepath, "F:\\hzv\\hezw\\a1.JPG", 57, 57, true);

	}
    /**���ͼƬ����Ϣ���Լ�СͼƬ�ļ��Ĵ�С*/
     public static void resizeTemp(String picpath){
        try {
            ImageInfo info = new ImageInfo(picpath);
            MagickImage image = new MagickImage(new ImageInfo(picpath));
            MagickImage scaled = null;// СͼƬ�ļ��Ĵ�С.

            Dimension dimension = image.getDimension();
            int iWi = (int) dimension.getWidth();
            int iHi = (int) dimension.getHeight();

             image.profileImage("*", null);// �Ƴ�ͼƬ��������Ϣ
                scaled = image.scaleImage(iWi, iHi);
              scaled.setFileName(picpath);
            info.setQuality(90);
            scaled.writeImage(info);
        } catch (MagickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
