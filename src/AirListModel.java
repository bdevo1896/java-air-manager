import javax.swing.AbstractListModel;

public class AirListModel extends AbstractListModel{
	
	AirDataManager adm;
	
	public AirListModel(AirDataManager adm){
		this.adm = adm;
	}

	@Override
	public Object getElementAt(int arg0) {
		return adm.monitorAt(arg0);
	}

	@Override
	public int getSize() {
		return adm.getMonitors().size();
	}
	
	public void fireChanges(){
		super.fireContentsChanged(this, 0, getSize());
	}

}
