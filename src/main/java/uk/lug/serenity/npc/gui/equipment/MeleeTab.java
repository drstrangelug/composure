/**
 * 
 */
package uk.lug.serenity.npc.gui.equipment;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import uk.lug.MutableList;
import uk.lug.data.DataModel;
import uk.lug.data.DataModelListener;
import uk.lug.serenity.npc.managers.EquipmentManager;
import uk.lug.serenity.npc.model.Person;
import uk.lug.serenity.npc.model.equipment.MeleeWeapon;

/**
 * $Id: This will be filled in on CVS commit $
 * @version $Revision: This will be filled in on CVS commit $
 * @author $Author: This will be filled in on CVS commit $
 * <p>
 * 
 */
/**
 * @author Luggy
 *
 */
public class MeleeTab extends JPanel implements DataModelListener<Person> {
	private static final long serialVersionUID = 1L;
	private EquipmentSelector<MeleeWeapon> selector;
	private DataModel<Person> model;
	private MutableList<MeleeWeapon> equipmentCatalog;
	private EquipmentSummaryPanel summaryPanel;
	
	/**
	 * Create a new equipment manager tab.
	 * @param dataModel
	 */
	public MeleeTab( DataModel<Person> dataModel ) {
		model= dataModel;
		model.addDataModelListener( this );
		equipmentCatalog = new MutableList<MeleeWeapon>();
		equipmentCatalog.addAll( EquipmentManager.getMeleeWeaponList() );
		makeGUI();
		
	}

	/**
	 * Build user interface
	 */
	private void makeGUI() {
		setLayout( new BorderLayout() );
		selector = new EquipmentSelector<MeleeWeapon>( new MutableList<MeleeWeapon>() , equipmentCatalog );
		selector.setName(EquipmentTab.EQUIPMENT_SELECTOR);
		add( selector, BorderLayout.CENTER );
		summaryPanel = new EquipmentSummaryPanel( model );
		add( summaryPanel, BorderLayout.NORTH );
	}

	/* (non-Javadoc)
	 * @see lug.data.DataModelListener#dataChanged(java.lang.Object, java.lang.Object)
	 */
	public void dataChanged(Person oldData, Person newData) {
		selector.setSelectedList( newData.getMeleeWeapons() );
	}
}
