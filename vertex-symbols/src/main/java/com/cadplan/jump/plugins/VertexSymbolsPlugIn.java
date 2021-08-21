package com.cadplan.jump.plugins;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.cadplan.icon.IconLoader;
import com.cadplan.jump.utils.VertexStyler;
import com.cadplan.jump.utils.LoadSymbolFiles;
import com.cadplan.jump.utils.VertexParams;
import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.plugin.AbstractPlugIn;
import com.vividsolutions.jump.workbench.plugin.EnableCheckFactory;
import com.vividsolutions.jump.workbench.plugin.MultiEnableCheck;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.GUIUtil;
import com.vividsolutions.jump.workbench.ui.MenuNames;

public class VertexSymbolsPlugIn extends AbstractPlugIn {

	public static ImageIcon ICON = IconLoader.icon("vsicon.gif");
	private static final I18N i18n = I18N.getInstance("com.cadplan");

	@Override
	public void initialize(PlugInContext context) throws Exception {
		EnableCheckFactory enableCheckFactory = context.getCheckFactory();
		MultiEnableCheck multiEnableCheck = new MultiEnableCheck();
		multiEnableCheck.add(enableCheckFactory.createAtLeastNLayersMustExistCheck(1));
		multiEnableCheck.add(enableCheckFactory.createAtLeastNLayersMustBeSelectedCheck(1));
		String str = MenuNames.PLUGINS;
		context.getFeatureInstaller().addMainMenuPlugin(this, new String[]{str, MenuNames.STYLE}, this.getName(), false, ICON, multiEnableCheck);
		//load symbol files (wkt and images) from OJ/lib/ext/VertexImages folsed

		LoadSymbolFiles loadSymbols = new LoadSymbolFiles(context);
		loadSymbols.start();
		VertexParams.context = context.getWorkbenchContext();
	}

	@Override
	public String getName() {
		return i18n.get("VertexSymbols.MenuItem");
	}

	@Override
	public boolean execute(PlugInContext context) {
		Layer layer ;
		if (context.getSelectedLayers().length ==0) {
			JOptionPane.showMessageDialog(null,
					i18n.get("VertexNote.Dialog.Message2"), "Warning...", 2);
			return false;
		}
		layer = context.getSelectedLayer(0); 

		//Read previous style from layer
		VertexStyler symbols = new VertexStyler();
		symbols.setLayer(layer);
		symbols.readStyle(); 

		//Activate dialog
		VertexSymbolsDialog vertexDialog = new VertexSymbolsDialog();
		//[Giuseppe Aruta 2020-05-28]  vertexDialog.updateSideBarIconAndDescription();
		if (vertexDialog.cancelled) {
			return false;
		} else {
			GUIUtil.centreOnWindow(vertexDialog);
			symbols.changeStyle();
			return true;
		}
	}
}
