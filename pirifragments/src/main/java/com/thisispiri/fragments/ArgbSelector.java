package com.thisispiri.fragments;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import static android.graphics.Color.alpha;
import static android.graphics.Color.argb;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;
/**A basic {@code ViewGroup} for letting the user select a color by manipulating ARGB values. Includes a preview of and a getter and setter for the color.*/
public class ArgbSelector extends LinearLayout {
	/**An empty {@code View} that shows the color currently selected.*/
	private View preview;
	private NumberPicker aEdit, rEdit, gEdit, bEdit;
	public ArgbSelector(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(VERTICAL);
		inflate(getContext(), R.layout.argb_selector, this);
		preview = findViewById(R.id.colorPreview);
		aEdit = findViewById(R.id.aEdit); rEdit = findViewById(R.id.rEdit); gEdit = findViewById(R.id.gEdit); bEdit = findViewById(R.id.bEdit);
		aEdit.setMinValue(0); aEdit.setMaxValue(255);
		rEdit.setMinValue(0); rEdit.setMaxValue(255);
		gEdit.setMinValue(0); gEdit.setMaxValue(255);
		bEdit.setMinValue(0); bEdit.setMaxValue(255);
		ArgbSelector.ColorListener cLis = new ArgbSelector.ColorListener();
		aEdit.setOnValueChangedListener(cLis);
		rEdit.setOnValueChangedListener(cLis);
		gEdit.setOnValueChangedListener(cLis);
		bEdit.setOnValueChangedListener(cLis);
	}
	/**Listens for changes in values of {@code NumberPicker}s in the {@code Fragment} and updates {@link ArgbSelector#preview} accordingly.*/
	private class ColorListener implements NumberPicker.OnValueChangeListener {
		@Override public void onValueChange(final NumberPicker picker, final int oldVal, final int newVal) {
			preview.setBackgroundColor(argb(aEdit.getValue(), rEdit.getValue(), gEdit.getValue(), bEdit.getValue()));
		}
	}
	/**Returns the current color.
	 * @return The current color.*/
	public int getColor() {return argb(aEdit.getValue(), rEdit.getValue(), gEdit.getValue(), bEdit.getValue());}
	/**Sets the values in the {@code NumberPicker}s to the supplied value and updates {@link ArgbSelector#preview}.
	 *@param color The desired color.*/
	public void setColor(final int color) {
		aEdit.setValue(alpha(color)); rEdit.setValue(red(color)); gEdit.setValue(green(color)); bEdit.setValue(blue(color));
		preview.setBackgroundColor(color);
	}
}
