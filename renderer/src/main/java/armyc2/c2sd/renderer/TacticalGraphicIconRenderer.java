package armyc2.c2sd.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import armyc2.c2sd.graphics2d.Point2D;
import armyc2.c2sd.renderer.utilities.Color;
import armyc2.c2sd.renderer.utilities.ImageInfo;
import armyc2.c2sd.renderer.utilities.SymbolUtilities;
import armyc2.c2sd.renderer.utilities.TacticalGraphicLookup;

public class TacticalGraphicIconRenderer {
	
	//font size of 60 produces a 40x40 pixel image.
    private static int fontSizeForTGIcons = 60;
    private static Typeface _TGMP = null;
	private static final Object _TGFontMutex = new Object();

	public static ImageInfo getIcon(String symbolID, int size, Color color, int symStd, int outlineWidth, Color outlineColor)
	{
		ImageInfo ii = null;
		
		
		
        if(armyc2.c2sd.renderer.utilities.SymbolUtilities.isWeather(symbolID)==true)
        {
            color = SymbolUtilities.getFillColorOfWeather(symbolID);
            if(color == null)
                color = SymbolUtilities.getLineColorOfWeather(symbolID);
        }//*/
        else if(color == null)
        {
            color = SymbolUtilities.getLineColorOfAffiliation(symbolID);
        }
		

        int charSymbolIndex = TacticalGraphicLookup.getInstance().getCharCodeFromSymbol(symbolID, symStd);
		
        if(charSymbolIndex > 0)
        {
        	float fontSize = fontSizeForTGIcons;
        	
        	//font size of 60 produces a 40x40 pixel image.
            float ratio = size/40.0f;
        	
            Point centerPoint = new Point(size/2, size/2);

            
          //resize to pixels
            if(ratio > 0)
            {
                fontSize = fontSize * ratio;
            }

            //fontSize = (fontSize/96 * 72);
            
            Paint fillPaint = null;
            fillPaint = new Paint();
			fillPaint.setStyle(Paint.Style.FILL);
			fillPaint.setColor(color.toARGB());
			fillPaint.setTextSize(fontSize);
			fillPaint.setAntiAlias(true);
			fillPaint.setTextAlign(Align.CENTER);
			fillPaint.setTypeface(_TGMP);
            
          //Draw glyphs to bitmap
			Bitmap bmp = Bitmap.createBitmap((int)(size), (int)(size), Config.ARGB_8888);
			Canvas canvas = new Canvas(bmp);
			
			String strSymbol = null;
			if(charSymbolIndex >0)
				strSymbol = String.valueOf((char)charSymbolIndex);
		
			

			if(strSymbol != null)
            {
                synchronized(_TGFontMutex)
                {
                    if(outlineWidth > 0)
                    {
                        Paint tempFill = new Paint();
                        tempFill.setStyle(Paint.Style.FILL_AND_STROKE);
                        tempFill.setStrokeWidth(outlineWidth);
                        tempFill.setColor(outlineColor.toARGB());
                        tempFill.setTextSize(fontSize);
                        tempFill.setAntiAlias(true);
                        tempFill.setTextAlign(Align.CENTER);
                        tempFill.setTypeface(_TGMP);

                        canvas.drawText(strSymbol, centerPoint.x, centerPoint.y, tempFill);
                    }
                    canvas.drawText(strSymbol, centerPoint.x, centerPoint.y, fillPaint);
                }
            }


			ii = new ImageInfo(bmp, centerPoint, new Rect(0, 0, size, size));
        }
        
		return ii;
	}
	
	public static void setTGTypeFace(Typeface tgtf)
	{
		_TGMP = tgtf;
	}
}
