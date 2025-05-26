import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SheRa {

    private JPanel pGeneral;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldID;
    private JTextField textFieldAlias;
    private JComboBox comboBoxPoder;
    private JComboBox comboBoxNivel;
    private JComboBox comboBoxUbi;
    private JTable JTableRegistro;
    private JTextField textFieldIDBuscar;
    private JButton buscarGuerreraButton;
    private JComboBox comboBoxPoderFiltar;
    private JTable tableFiltrar;
    private JButton filtrarButton;
    private JComboBox comboBox1;
    private JButton conteoButton;
    private JTextArea textAreaConteo;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JPanel pModificar;
    private JButton modificarButton;
    private JTextField textFieldIdModificable;
    private JTextField textFieldAliasModificable;
    private JComboBox comboBoxPoderModificable;
    private JComboBox comboBoxNivelModificable;
    private JComboBox comboBoxUbiModificable;

    private ListaGuerrerasBright lista = new ListaGuerrerasBright();

    public SheRa() {

        DefaultTableModel df = new DefaultTableModel(new Object[]{"ID", "Alias", "Poder", "Nivel", "Ubicación"}, 0);
        JTableRegistro.setModel(df);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldID.getText().trim());
                    String alias = textFieldAlias.getText().trim();
                    String poder = comboBoxPoder.getSelectedItem().toString();
                    int nivel = Integer.parseInt(comboBoxNivel.getSelectedItem().toString());
                    String ubicacion = comboBoxUbi.getSelectedItem().toString();

                    GuerreraBrightMoon nueva = new GuerreraBrightMoon(id, alias, poder, nivel, ubicacion);

                    if (lista.insertarGuerrera(nueva)) {
                        JOptionPane.showMessageDialog(null, "Guerrera registrada exitosamente");
                        actualizarTablaRegistro();
                        limpiarFormulario();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, No se pudo registrar.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos ingresados.");
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        buscarGuerreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idBuscar = Integer.parseInt(textFieldIDBuscar.getText().trim());
                    GuerreraBrightMoon encontrada = lista.buscarBinaria(idBuscar);

                    if (encontrada != null) {
                        cargarDatosAModificar(encontrada);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró guerrera con ese ID");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poderSeleccionado = comboBoxPoderFiltar.getSelectedItem().toString();
                ArrayList<GuerreraBrightMoon> filtradas = lista.especialidad(poderSeleccionado);
                lista.ordenarPorNivel(filtradas);

                DefaultTableModel modelo = new DefaultTableModel();
                modelo.setColumnIdentifiers(new Object[]{"ID", "Alias", "Poder", "Nivel", "Ubicación"});
                for (GuerreraBrightMoon g : filtradas) {
                    modelo.addRow(new Object[]{g.getId(), g.getAlias(), g.getPoderBatalla(), g.getNivelEstrategia(), g.getUbicacion()});
                }
                tableFiltrar.setModel(modelo);
            }
        });

        conteoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] ubicaciones = new String[comboBox1.getItemCount()];
                for (int i = 0; i < comboBox1.getItemCount(); i++) {
                    ubicaciones[i] = comboBox1.getItemAt(i).toString();
                }

                StringBuilder conteo = new StringBuilder();
                for (String ubic : ubicaciones) {
                    int total = lista.contarUbicacion(ubic);
                    conteo.append(ubic).append(": ").append(total).append("\n");
                }
                textAreaConteo.setText(conteo.toString());
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textFieldIdModificable.getText().trim());
                    String alias = textFieldAliasModificable.getText().trim();
                    String poder = comboBoxPoderModificable.getSelectedItem().toString();
                    int nivel = Integer.parseInt(comboBoxNivelModificable.getSelectedItem().toString());
                    String ubicacion = comboBoxUbiModificable.getSelectedItem().toString();

                    GuerreraBrightMoon modificada = new GuerreraBrightMoon(id, alias, poder, nivel, ubicacion);

                    if (lista.modificarGuerrera(modificada)) {
                        JOptionPane.showMessageDialog(null, "Guerrera modificada exitosamente");
                        actualizarTablaRegistro();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar. Verifica el ID.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos. Revisa los campos.");
                }
            }
        });

    }

    private void actualizarTablaRegistro() {
        DefaultTableModel modelo = (DefaultTableModel) JTableRegistro.getModel();
        modelo.setRowCount(0);
        NodoDoble aux = lista.getStart();
        while (aux != null) {
            GuerreraBrightMoon g = aux.guerreraBri;
            modelo.addRow(new Object[]{g.getId(), g.getAlias(), g.getPoderBatalla(), g.getNivelEstrategia(), g.getUbicacion()});
            aux = aux.sig;
        }
    }

    private void limpiarFormulario() {
        textFieldID.setText("");
        textFieldAlias.setText("");
        comboBoxPoder.setSelectedIndex(0);
        comboBoxNivel.setSelectedIndex(0);
        comboBoxUbi.setSelectedIndex(0);
    }

    private void cargarDatosAModificar(GuerreraBrightMoon g) {
        textFieldIdModificable.setText(String.valueOf(g.getId()));
        textFieldIdModificable.setEditable(false);
        textFieldAliasModificable.setText(g.getAlias());
        comboBoxPoderModificable.setSelectedItem(g.getPoderBatalla());
        comboBoxNivelModificable.setSelectedItem(String.valueOf(g.getNivelEstrategia()));
        comboBoxUbiModificable.setSelectedItem(g.getUbicacion());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SheRa");
        frame.setContentPane(new SheRa().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

