
package autos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class crud extends JFrame implements ActionListener {

    JLabel lbl, lbID, lbMarca, lbPlacas;
    JLabel lbModelo, lbMotor, lbSerie, lbEco;
    JLabel lbCapacidad, lbTipo;

    JTextField txtID, txtMarca, txtPlacas;
    JTextField txtModelo, txtMotor, txtSerie;
    JTextField txtEco, txtCapacidad, txtTipo;

    JButton btnCrear, btnLeer, btnActualizar, btnEliminar;

    JTable tabla;
    JScrollPane scrollPane;
    DefaultTableModel modelo;

    JMenuBar barraMenu;
    JMenu menuCRUD;

    JMenuItem menuCrear, menuLeer, menuActualizar, menuEliminar;

    int idSeleccionado = -1;

    public crud() {

        this.setTitle("CRUD de Autos");
        this.setBounds(100, 50, 1000, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        this.getContentPane().setBackground(
                java.awt.Color.decode("#2b2b2b")
        );

        this.barraMenu = new JMenuBar();

        this.menuCRUD = new JMenu("CRUD");

        this.menuCrear = new JMenuItem("Crear");
        this.menuLeer = new JMenuItem("Consultar");
        this.menuActualizar = new JMenuItem("Actualizar");
        this.menuEliminar = new JMenuItem("Eliminar");

        this.menuCRUD.add(this.menuCrear);
        this.menuCRUD.add(this.menuLeer);
        this.menuCRUD.add(this.menuActualizar);
        this.menuCRUD.add(this.menuEliminar);

        this.barraMenu.add(this.menuCRUD);

        this.setJMenuBar(this.barraMenu);

        this.menuCrear.addActionListener(this);
        this.menuLeer.addActionListener(this);
        this.menuActualizar.addActionListener(this);
        this.menuEliminar.addActionListener(this);

        this.lbl = new JLabel("CRUD de Autos");
        this.lbl.setBounds(450, 15, 250, 30);
        this.add(this.lbl);

        this.txtID = new JTextField();
        this.txtID.setEditable(false);
        this.txtID.setVisible(false);
        this.add(this.txtID);

        this.lbMarca = new JLabel("ID Marca");
        this.lbMarca.setBounds(180, 65, 100, 25);
        this.add(this.lbMarca);

        this.txtMarca = new JTextField();
        this.txtMarca.setBounds(260, 65, 200, 25);
        this.add(this.txtMarca);

        this.lbPlacas = new JLabel("Placas");
        this.lbPlacas.setBounds(180, 105, 100, 25);
        this.add(this.lbPlacas);

        this.txtPlacas = new JTextField();
        this.txtPlacas.setBounds(260, 105, 200, 25);
        this.add(this.txtPlacas);

        this.lbModelo = new JLabel("Modelo");
        this.lbModelo.setBounds(180, 145, 100, 25);
        this.add(this.lbModelo);

        this.txtModelo = new JTextField();
        this.txtModelo.setBounds(260, 145, 200, 25);
        this.add(this.txtModelo);

        this.lbMotor = new JLabel("Motor");
        this.lbMotor.setBounds(180, 185, 100, 25);
        this.add(this.lbMotor);

        this.txtMotor = new JTextField();
        this.txtMotor.setBounds(260, 185, 200, 25);
        this.add(this.txtMotor);

        this.lbSerie = new JLabel("Serie");
        this.lbSerie.setBounds(550, 65, 100, 25);
        this.add(this.lbSerie);

        this.txtSerie = new JTextField();
        this.txtSerie.setBounds(625, 65, 200, 25);
        this.add(this.txtSerie);

        this.lbEco = new JLabel("Eco");
        this.lbEco.setBounds(550, 105, 100, 25);
        this.add(this.lbEco);

        this.txtEco = new JTextField();
        this.txtEco.setBounds(625, 105, 200, 25);
        this.add(this.txtEco);

        this.lbTipo = new JLabel("Tipo");
        this.lbTipo.setBounds(550, 145, 100, 25);
        this.add(this.lbTipo);

        this.txtTipo = new JTextField();
        this.txtTipo.setBounds(625, 145, 200, 25);
        this.add(this.txtTipo);

        this.lbCapacidad = new JLabel("Capacidad");
        this.lbCapacidad.setBounds(550, 185, 100, 25);
        this.add(this.lbCapacidad);

        this.txtCapacidad = new JTextField();
        this.txtCapacidad.setBounds(625, 185, 200, 25);
        this.add(this.txtCapacidad);

        this.btnCrear = new JButton("Crear");
        this.btnCrear.setBounds(200, 260, 140, 30);
        this.add(this.btnCrear);

        this.btnLeer = new JButton("Consultar");
        this.btnLeer.setBounds(345, 260, 140, 30);
        this.add(this.btnLeer);

        this.btnActualizar = new JButton("Actualizar");
        this.btnActualizar.setBounds(490, 260, 140, 30);
        this.add(this.btnActualizar);

        this.btnEliminar = new JButton("Eliminar");
        this.btnEliminar.setBounds(635, 260, 140, 30);
        this.add(this.btnEliminar);

        this.btnCrear.addActionListener(this);
        this.btnLeer.addActionListener(this);
        this.btnActualizar.addActionListener(this);
        this.btnEliminar.addActionListener(this);

        this.modelo = new DefaultTableModel();

        this.modelo.addColumn("ID");
        this.modelo.addColumn("ID Marca");
        this.modelo.addColumn("Placas");
        this.modelo.addColumn("Modelo");
        this.modelo.addColumn("Motor");
        this.modelo.addColumn("Serie");
        this.modelo.addColumn("Eco");
        this.modelo.addColumn("Capacidad");
        this.modelo.addColumn("Tipo");

        this.tabla = new JTable(this.modelo);

        this.scrollPane = new JScrollPane(this.tabla);
        this.scrollPane.setBounds(30, 325, 920, 280);
        this.scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        java.awt.Color.BLACK, 1
                )
        );

        this.add(this.scrollPane);

        this.tabla.addMouseListener(
                new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(
                    java.awt.event.MouseEvent e) {

                int fila = tabla.getSelectedRow();

                if (fila >= 0) {

                    idSeleccionado = Integer.parseInt(
                            tabla.getValueAt(fila, 0).toString()
                    );

                    txtID.setText(
                            String.valueOf(idSeleccionado)
                    );

                    txtMarca.setText(
                            tabla.getValueAt(fila, 1).toString()
                    );

                    txtPlacas.setText(
                            tabla.getValueAt(fila, 2).toString()
                    );

                    txtModelo.setText(
                            tabla.getValueAt(fila, 3).toString()
                    );

                    txtMotor.setText(
                            tabla.getValueAt(fila, 4).toString()
                    );

                    txtSerie.setText(
                            tabla.getValueAt(fila, 5).toString()
                    );

                    txtEco.setText(
                            tabla.getValueAt(fila, 6).toString()
                    );

                    txtCapacidad.setText(
                            tabla.getValueAt(fila, 7).toString()
                    );

                    txtTipo.setText(
                            tabla.getValueAt(fila, 8).toString()
                    );
                }
            }
        });

        consultar();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {

        Object origen = e.getSource();

        if (origen == this.btnCrear
                || origen == this.menuCrear) {

            crear();

        } else if (origen == this.btnLeer
                || origen == this.menuLeer) {

            consultar();

        } else if (origen == this.btnActualizar
                || origen == this.menuActualizar) {

            actualizar();

        } else if (origen == this.btnEliminar
                || origen == this.menuEliminar) {

            eliminar();
        }
    }

    private Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost/polizas",
                "root",
                ""
        );
    }

    public void crear() {

        if (txtMarca.getText().trim().isEmpty()
                || txtPlacas.getText().trim().isEmpty()
                || txtModelo.getText().trim().isEmpty()
                || txtMotor.getText().trim().isEmpty()
                || txtSerie.getText().trim().isEmpty()
                || txtEco.getText().trim().isEmpty()
                || txtCapacidad.getText().trim().isEmpty()
                || txtTipo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Completa todos los campos."
            );

            return;
        }

        String sql = "INSERT INTO autos "
                + "(id_marca, placas, modelo, motor, serie, eco, capacidad, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection cnx = conectar();
                PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    Integer.parseInt(txtMarca.getText().trim())
            );

            ps.setString(2, txtPlacas.getText().trim());
            ps.setString(3, txtModelo.getText().trim());
            ps.setString(4, txtMotor.getText().trim());
            ps.setString(5, txtSerie.getText().trim());
            ps.setString(6, txtEco.getText().trim());

            ps.setInt(
                    7,
                    Integer.parseInt(txtCapacidad.getText().trim())
            );

            ps.setString(8, txtTipo.getText().trim());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Registro creado correctamente."
            );

            consultar();
            limpiar();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID Marca y Capacidad deben ser números."
            );

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage()
            );
        }
    }

    public void consultar() {

        String sql = "SELECT id, id_marca, placas, modelo, motor, "
                + "serie, eco, capacidad, tipo FROM autos ORDER BY id";

        try (
                Connection cnx = conectar();
                PreparedStatement ps = cnx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            this.modelo.setRowCount(0);

            while (rs.next()) {

                this.modelo.addRow(
                        new Object[]{
                            rs.getInt("id"),
                            rs.getInt("id_marca"),
                            rs.getString("placas"),
                            rs.getString("modelo"),
                            rs.getString("motor"),
                            rs.getString("serie"),
                            rs.getString("eco"),
                            rs.getInt("capacidad"),
                            rs.getString("tipo")
                        }
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage()
            );
        }
    }

    public void actualizar() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un registro de la tabla."
            );

            return;
        }

        if (txtMarca.getText().trim().isEmpty()
                || txtPlacas.getText().trim().isEmpty()
                || txtModelo.getText().trim().isEmpty()
                || txtMotor.getText().trim().isEmpty()
                || txtSerie.getText().trim().isEmpty()
                || txtEco.getText().trim().isEmpty()
                || txtCapacidad.getText().trim().isEmpty()
                || txtTipo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Completa todos los campos."
            );

            return;
        }

        String sql = "UPDATE autos SET id_marca=?, placas=?, modelo=?, "
                + "motor=?, serie=?, eco=?, capacidad=?, tipo=? WHERE id=?";

        try (
                Connection cnx = conectar();
                PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    Integer.parseInt(txtMarca.getText().trim())
            );

            ps.setString(2, txtPlacas.getText().trim());
            ps.setString(3, txtModelo.getText().trim());
            ps.setString(4, txtMotor.getText().trim());
            ps.setString(5, txtSerie.getText().trim());
            ps.setString(6, txtEco.getText().trim());

            ps.setInt(
                    7,
                    Integer.parseInt(txtCapacidad.getText().trim())
            );

            ps.setString(8, txtTipo.getText().trim());
            ps.setInt(9, idSeleccionado);

            int filas = ps.executeUpdate();

            if (filas > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registro actualizado correctamente."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el registro."
                );
            }

            consultar();
            limpiar();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID Marca y Capacidad deben ser números."
            );

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage()
            );
        }
    }

    public void eliminar() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un registro de la tabla."
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar este registro?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM autos WHERE id=?";

        try (
                Connection cnx = conectar();
                PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setInt(1, idSeleccionado);

            int filas = ps.executeUpdate();

            if (filas > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registro eliminado correctamente."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró el registro."
                );
            }

            consultar();
            limpiar();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + ex.getMessage()
            );
        }
    }

    private void limpiar() {

        this.idSeleccionado = -1;

        this.txtID.setText("");
        this.txtMarca.setText("");
        this.txtPlacas.setText("");
        this.txtModelo.setText("");
        this.txtMotor.setText("");
        this.txtSerie.setText("");
        this.txtEco.setText("");
        this.txtCapacidad.setText("");
        this.txtTipo.setText("");

        this.tabla.clearSelection();
    }
}