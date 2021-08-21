package com.cadplan.jump.plugins.panel;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.vividsolutions.jump.I18N;
import org.openjump.core.ui.plugin.layer.LayerPropertiesPlugIn.PropertyPanel;
import org.saig.core.gui.swing.sldeditor.util.FormUtils;

import com.cadplan.jump.utils.StyleUtils;
import com.cadplan.jump.utils.VertexParams;
import com.cadplan.jump.utils.VertexStyler;
import com.vividsolutions.jump.feature.AttributeType;
import com.vividsolutions.jump.feature.FeatureSchema;


public class VertexParametersPanel extends JPanel implements PropertyPanel {

	I18N i18n = I18N.getInstance("com.cadplan");
	public JLabel sizeLabel, orienLabel,  baseScaleLabel, lineLabel;
	public JTextField sizeField, orienField, baseScaleField;
	public JCheckBox showLineCB, showFillCB, dottedCB, sizeByScaleCB;
	private JLabel styleLabel;
	private final JTextField styleField;
	public JRadioButton absValueRB;
	public JRadioButton byAttributeRB;
	public JComboBox<String> attributeCB;
	public ButtonGroup rotateGroup;

	public JLabel distanceLabel;
	public JLabel offsetLabel;
	public JCheckBox rotationCB;
	public JFormattedTextField distanceField;
	public JFormattedTextField offsetField;
	private static final long serialVersionUID = 1L;

	public VertexParametersPanel() {
		this.setLayout(new GridBagLayout());

		this.styleLabel = new JLabel(i18n.get("VertexSymbols.SymbolName"));
		this.styleField = new JTextField();
		this.styleField.setMinimumSize(new Dimension(75, 20));
		this.styleField.setEditable(false);
		this.showLineCB = new JCheckBox(i18n.get("VertexSymbols.Dialog.ShowLine"));
		this.showFillCB = new JCheckBox(i18n.get("VertexSymbols.Dialog.ShowFill"));
		this.dottedCB = new JCheckBox(i18n.get("VertexSymbols.Dialog.Dotted"));
		FormUtils.addRowInGBL(this, 0, 0, this.styleLabel, this.styleField, false);
		FormUtils.addRowInGBL(this, 0, 2, this.dottedCB,  this.showLineCB, false);
		FormUtils.addRowInGBL(this, 0, 4,  this.showFillCB);



		this.orienLabel = new JLabel(i18n.get("VertexSymbols.Dialog.Orientation") + ": ");
		this.absValueRB = new JRadioButton(i18n.get("VertexSymbols.Dialog.ByValue"));
		this.orienField = StyleUtils.doubleField(5);
		this.byAttributeRB = new JRadioButton(i18n.get("VertexSymbols.Dialog.ByAttribute"));
		this.rotateGroup = new ButtonGroup();
		this.rotateGroup.add(this.absValueRB);
		this.rotateGroup.add(this.byAttributeRB);
		this.absValueRB.setSelected(true);
		this.attributeCB = new JComboBox<>();
		FormUtils.addRowInGBL(this, 2, 0, this.orienLabel, this.absValueRB, false);
		FormUtils.addRowInGBL(this, 2, 2, this.orienField, this.byAttributeRB, false);
		FormUtils.addRowInGBL(this, 2, 4, this.attributeCB);
		this.sizeLabel = new JLabel(i18n.get("VertexSymbols.Dialog.Size") + ": ");
		this.sizeField = StyleUtils.integerField(5);
		this.baseScaleLabel = new JLabel(i18n.get("VertexSymbols.Dialog.BaseScale") + ": ");
		this.baseScaleField = StyleUtils.doubleField(5);
		this.baseScaleField.setText(String.valueOf(VertexParams.baseScale));
		this.sizeByScaleCB = new JCheckBox(i18n.get("VertexSymbols.Dialog.SizeByScale"));
		this.sizeByScaleCB.setSelected(VertexParams.sizeByScale);
		FormUtils.addRowInGBL(this, 1, 0, this.sizeLabel, this.sizeField, false);
		FormUtils.addRowInGBL(this, 1, 2, this.baseScaleLabel, this.baseScaleField, false);
		FormUtils.addRowInGBL(this, 1, 4, this.sizeByScaleCB);

		String line ="<html><font color=black size=3>"
				+ "<b>" + i18n.get("VertexSymbols.Dialog.line-decoration") + "</b></html>";
		this.lineLabel = new JLabel(line);
		this.distanceField = new JFormattedTextField();
		this.offsetField = new JFormattedTextField();
		this.distanceField.setColumns(5); 
		this.offsetField.setColumns(5); 
		this.offsetLabel= new JLabel(i18n.get("VertexSymbols.Dialog.line-offset") + ": ");
		this.distanceLabel= new JLabel(i18n.get("VertexSymbols.Dialog.line-distance") + ": ");
		this.rotationCB = new JCheckBox(i18n.get("VertexSymbols.Dialog.line-rotate"));
		lineLabel.setEnabled(VertexParams.lineDecoration);
		distanceField.setEnabled(VertexParams.lineDecoration);
		offsetField.setEnabled(VertexParams.lineDecoration);
		distanceLabel.setEnabled(VertexParams.lineDecoration);
		offsetLabel.setEnabled(VertexParams.lineDecoration);
		rotationCB.setEnabled(VertexParams.lineDecoration);
		JPanel linePanel = new JPanel(new GridBagLayout());
		FormUtils.addRowInGBL(linePanel, 0, 0, this.distanceLabel, this.distanceField, false); 
		FormUtils.addRowInGBL(linePanel, 0, 2, this.offsetLabel, this.offsetField, false); 
		FormUtils.addRowInGBL(linePanel, 0,4, rotationCB);
		FormUtils.addRowInGBL(this, 3, 0, lineLabel, true, true);
		FormUtils.addFiller(this, 3, 1);
		FormUtils.addRowInGBL(this, 3, 2, linePanel, true, true);
		this.setValues();
	}




	public boolean bool = false;
	public void setValues() {
		this.sizeField.setText(String.valueOf(VertexParams.size));
		this.orienField.setText(String.valueOf(VertexParams.orientation));
		this.showLineCB.setSelected(VertexParams.showLine);
		this.showFillCB.setSelected(VertexParams.showFill);
		this.dottedCB.setSelected(VertexParams.dotted);
		this.sizeByScaleCB.setSelected(VertexParams.sizeByScale);
		this.byAttributeRB.setEnabled(VertexParams.singleLayer);
		distanceField.setValue(VertexParams.distance);
		offsetField.setValue(VertexParams.offset);
		rotationCB.setSelected(VertexParams.rotate);

		String rotationTooltip = StyleUtils.getName(i18n.get("VertexSymbols.Dialog.line-rotate"),
				i18n.get("VertexSymbols.Dialog.line-rotate-tooltip"));
		rotationCB.setToolTipText(rotationTooltip);

		String distanceTooltip = StyleUtils.getName(i18n.get("VertexSymbols.Dialog.line-distance"),
				i18n.get("VertexSymbols.Dialog.line-distance-tooltip"));
		distanceLabel.setToolTipText(distanceTooltip);

		String offsetTooltip = StyleUtils.getName(i18n.get("VertexSymbols.Dialog.line-offset"),
				i18n.get("VertexSymbols.Dialog.line-offset-tooltip"));
		offsetLabel.setToolTipText(offsetTooltip);
		bool = false;
		if (VertexParams.singleLayer && VertexParams.selectedLayer != null) {
			this.attributeCB.removeAllItems();
			FeatureSchema featureSchema = VertexParams.selectedLayer.getFeatureCollectionWrapper().getFeatureSchema();

			for(byte b = 0; b < featureSchema.getAttributeCount(); ++b) {
				AttributeType attributeType = featureSchema.getAttributeType(b);
				if (attributeType == AttributeType.DOUBLE || attributeType == AttributeType.INTEGER) {
					String str = featureSchema.getAttributeName(b);
					this.attributeCB.addItem(str);
					bool = true;
				}
			}

			if (VertexParams.attName != null && !VertexParams.attName.equals("")) {
				this.attributeCB.setSelectedItem(VertexParams.attName);
			}
		}

		if (VertexParams.byValue) {
			this.absValueRB.setSelected(true);
		} else {
			this.byAttributeRB.setSelected(true);
		}

		if (!VertexParams.singleLayer) {
			this.absValueRB.setSelected(true);
		}

		this.byAttributeRB.setEnabled(bool);
		this.attributeCB.setEnabled(bool);
		VertexStyler symbols = new VertexStyler();

		this.styleField.setText(symbols.styleName());

		//	this.symbolName.setText("<html><font color=black size=3>" + I18NPlug.getI18N("VertexSymbols.SymbolName") + "<b>" + symbols.styleName() + "</b></html>");
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public void updateStyles() {
	}

	@Override
	public String validateInput() {
		return null;
	}
}
