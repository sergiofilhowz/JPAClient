package br.com.jpa.table;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JpaClientTable extends JScrollPane {

	private static final long serialVersionUID = -3462036405519294234L;

	private JTable tabela;

	public JpaClientTable() {
		tabela = new JTable();

//		for(int i = 0; i < model.getColumns().size(); i++) {
//			String column = model.getColumns().get(i);
//			Class<?> type = model.getColumnTypes().get(i);
//			if(type.equals(Date.class)){
//				setColumnSize(i, 130);
//			}else {
//				setColumnSize(i, column.length() * 5);
//			}
//		}
		
		
		this.setViewportView(tabela);
	}
	
	public void setModel(ReflectionTableModel model) {
		tabela.setModel(model);
		model.fireTableStructureChanged();
	}
	
	public void setColumnSize(int column, int size){
		tabela.getColumnModel().getColumn(column).setMinWidth(50);
//		tabela.getColumnModel().getColumn(column).setMaxWidth(size);
		tabela.getColumnModel().getColumn(column).setPreferredWidth(size);
	}
}
