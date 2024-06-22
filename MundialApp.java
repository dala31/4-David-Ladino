import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MundialApp {
    private JFrame frame;
    private Mundial mundial;
    private JTabbedPane tabbedPane;

    // Componentes para la pestaña de Equipos
    private JTextField entryNombreEquipo;
    private JTextField entryEntrenadorEquipo;
    private JList<String> listaEquipos;
    private DefaultListModel<String> modeloListaEquipos;
    private JTextField entryNombreJugador;
    private JTextField entryEdadJugador;
    private JTextField entryPosicionJugador;

    // Componentes para la pestaña de Partidos
    private JComboBox<String> comboEquipoLocal;
    private JComboBox<String> comboEquipoVisitante;
    private JTextField entryResultadoPartido;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloListaPartidos;

    // Componentes para la pestaña de Grupos
    private JTextField entryNombreGrupo;
    private JList<String> listaGrupos;
    private DefaultListModel<String> modeloListaGrupos;

    // Componentes para la pestaña de Estadios
    private JTextField entryNombreEstadio;
    private JTextField entryCiudadEstadio;
    private JTextField entryCapacidadEstadio;
    private JList<String> listaEstadios;
    private DefaultListModel<String> modeloListaEstadios;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MundialApp();
            }
        });
    }

    public MundialApp() {
        mundial = new Mundial();
        inicializarGUI();
    }

    private void inicializarGUI() {
        frame = new JFrame("Mundial App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);

        inicializarTabEquipos();
        inicializarTabPartidos();
        inicializarTabGrupos();
        inicializarTabEstadios();

        frame.setVisible(true);
    }

    private void inicializarTabEquipos() {
        JPanel panelEquipos = new JPanel(new GridLayout(1, 2));
        
        // Panel para registrar equipos
        JPanel panelRegistroEquipo = new JPanel(new GridBagLayout());
        panelRegistroEquipo.setBorder(BorderFactory.createTitledBorder("Registrar Equipo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRegistroEquipo.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        entryNombreEquipo = new JTextField(20);
        panelRegistroEquipo.add(entryNombreEquipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistroEquipo.add(new JLabel("Entrenador:"), gbc);

        gbc.gridx = 1;
        entryEntrenadorEquipo = new JTextField(20);
        panelRegistroEquipo.add(entryEntrenadorEquipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnRegistrarEquipo = new JButton("Registrar Equipo");
        btnRegistrarEquipo.addActionListener(e -> registrarEquipo());
        panelRegistroEquipo.add(btnRegistrarEquipo, gbc);

        gbc.gridy = 3;
        modeloListaEquipos = new DefaultListModel<>();
        listaEquipos = new JList<>(modeloListaEquipos);
        JScrollPane scrollEquipos = new JScrollPane(listaEquipos);
        scrollEquipos.setPreferredSize(new Dimension(200, 150));
        panelRegistroEquipo.add(scrollEquipos, gbc);

        // Panel para registrar jugadores
        JPanel panelRegistroJugador = new JPanel(new GridBagLayout());
        panelRegistroJugador.setBorder(BorderFactory.createTitledBorder("Registrar Jugador"));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRegistroJugador.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        entryNombreJugador = new JTextField(20);
        panelRegistroJugador.add(entryNombreJugador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistroJugador.add(new JLabel("Edad:"), gbc);

        gbc.gridx = 1;
        entryEdadJugador = new JTextField(20);
        panelRegistroJugador.add(entryEdadJugador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRegistroJugador.add(new JLabel("Posición:"), gbc);

        gbc.gridx = 1;
        entryPosicionJugador = new JTextField(20);
        panelRegistroJugador.add(entryPosicionJugador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnRegistrarJugador = new JButton("Registrar Jugador");
        btnRegistrarJugador.addActionListener(e -> registrarJugador());
        panelRegistroJugador.add(btnRegistrarJugador, gbc);

        panelEquipos.add(panelRegistroEquipo);
        panelEquipos.add(panelRegistroJugador);

        tabbedPane.addTab("Equipos", panelEquipos);
    }

    private void inicializarTabPartidos() {
        JPanel panelPartidos = new JPanel(new GridBagLayout());
        panelPartidos.setBorder(BorderFactory.createTitledBorder("Gestionar Partido"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPartidos.add(new JLabel("Equipo Local:"), gbc);

        gbc.gridx = 1;
        comboEquipoLocal = new JComboBox<>();
        panelPartidos.add(comboEquipoLocal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPartidos.add(new JLabel("Equipo Visitante:"), gbc);

        gbc.gridx = 1;
        comboEquipoVisitante = new JComboBox<>();
        panelPartidos.add(comboEquipoVisitante, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPartidos.add(new JLabel("Resultado:"), gbc);

        gbc.gridx = 1;
        entryResultadoPartido = new JTextField(20);
        panelPartidos.add(entryResultadoPartido, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnRegistrarPartido = new JButton("Registrar Partido");
        btnRegistrarPartido.addActionListener(e -> registrarPartido());
        panelPartidos.add(btnRegistrarPartido, gbc);

        gbc.gridy = 4;
        modeloListaPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloListaPartidos);
        JScrollPane scrollPartidos = new JScrollPane(listaPartidos);
        scrollPartidos.setPreferredSize(new Dimension(300, 150));
        panelPartidos.add(scrollPartidos, gbc);

        tabbedPane.addTab("Partidos", panelPartidos);
    }

    private void inicializarTabGrupos() {
        JPanel panelGrupos = new JPanel(new GridBagLayout());
        panelGrupos.setBorder(BorderFactory.createTitledBorder("Registrar Grupo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelGrupos.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        entryNombreGrupo = new JTextField(20);
        panelGrupos.add(entryNombreGrupo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton btnRegistrarGrupo = new JButton("Registrar Grupo");
        btnRegistrarGrupo.addActionListener(e -> registrarGrupo());
        panelGrupos.add(btnRegistrarGrupo, gbc);

        gbc.gridy = 2;
        modeloListaGrupos = new DefaultListModel<>();
        listaGrupos = new JList<>(modeloListaGrupos);
        JScrollPane scrollGrupos = new JScrollPane(listaGrupos);
        scrollGrupos.setPreferredSize(new Dimension(300, 150));
        panelGrupos.add(scrollGrupos, gbc);

        tabbedPane.addTab("Grupos", panelGrupos);
    }

    private void inicializarTabEstadios() {
        JPanel panelEstadios = new JPanel(new GridBagLayout());
        panelEstadios.setBorder(BorderFactory.createTitledBorder("Registrar Estadio"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEstadios.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        entryNombreEstadio = new JTextField(20);
        panelEstadios.add(entryNombreEstadio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEstadios.add(new JLabel("Ciudad:"), gbc);

        gbc.gridx = 1;
        entryCiudadEstadio = new JTextField(20);
        panelEstadios.add(entryCiudadEstadio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEstadios.add(new JLabel("Capacidad:"), gbc);

        gbc.gridx = 1;
        entryCapacidadEstadio = new JTextField(20);
        panelEstadios.add(entryCapacidadEstadio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnRegistrarEstadio = new JButton("Registrar Estadio");
        btnRegistrarEstadio.addActionListener(e -> registrarEstadio());
        panelEstadios.add(btnRegistrarEstadio, gbc);

        gbc.gridy = 4;
        modeloListaEstadios = new DefaultListModel<>();
        listaEstadios = new JList<>(modeloListaEstadios);
        JScrollPane scrollEstadios = new JScrollPane(listaEstadios);
        scrollEstadios.setPreferredSize(new Dimension(300, 150));
        panelEstadios.add(scrollEstadios, gbc);

        tabbedPane.addTab("Estadios", panelEstadios);
    }

    private void registrarEquipo() {
        String nombre = entryNombreEquipo.getText();
        String entrenador = entryEntrenadorEquipo.getText();
        if (!nombre.isEmpty() && !entrenador.isEmpty()) {
            Equipo equipo = new Equipo(nombre, entrenador);
            mundial.getGrupos().get(0).agregarEquipo(equipo); // Asumimos que el grupo ya existe
            modeloListaEquipos.addElement(equipo.getNombre());
            entryNombreEquipo.setText("");
            entryEntrenadorEquipo.setText("");
            actualizarComboBoxEquipos();
        }
    }

    private void registrarJugador() {
        String nombre = entryNombreJugador.getText();
        String edad = entryEdadJugador.getText();
        String posicion = entryPosicionJugador.getText();
        if (!nombre.isEmpty() && !edad.isEmpty() && !posicion.isEmpty()) {
            Jugador jugador = new Jugador(nombre, edad, posicion);
            int equipoIndex = listaEquipos.getSelectedIndex();
            if (equipoIndex != -1) {
                Equipo equipo = mundial.getGrupos().get(0).getEquipos().get(equipoIndex);
                equipo.agregarJugador(jugador);
                entryNombreJugador.setText("");
                entryEdadJugador.setText("");
                entryPosicionJugador.setText("");
            }
        }
    }

    private void registrarPartido() {
        String equipoLocal = (String) comboEquipoLocal.getSelectedItem();
        String equipoVisitante = (String) comboEquipoVisitante.getSelectedItem();
        String resultado = entryResultadoPartido.getText();
        if (equipoLocal != null && equipoVisitante != null && !resultado.isEmpty()) {
            Equipo local = buscarEquipo(equipoLocal);
            Equipo visitante = buscarEquipo(equipoVisitante);
            if (local != null && visitante != null) {
                Partido partido = new Partido(local, visitante);
                partido.jugarPartido(resultado);
                modeloListaPartidos.addElement(partido.mostrarResultado());
                entryResultadoPartido.setText("");
            }
        }
    }

    private void registrarGrupo() {
        String nombre = entryNombreGrupo.getText();
        if (!nombre.isEmpty()) {
            Grupo grupo = new Grupo(nombre);
            mundial.registrarGrupo(grupo);
            modeloListaGrupos.addElement(grupo.getNombre());
            entryNombreGrupo.setText("");
        }
    }

    private void registrarEstadio() {
        String nombre = entryNombreEstadio.getText();
        String ciudad = entryCiudadEstadio.getText();
        String capacidad = entryCapacidadEstadio.getText();
        if (!nombre.isEmpty() && !ciudad.isEmpty() && !capacidad.isEmpty()) {
            Estadio estadio = new Estadio(nombre, ciudad, capacidad);
            mundial.registrarEstadio(estadio);
            modeloListaEstadios.addElement(estadio.mostrarInfo());
            entryNombreEstadio.setText("");
            entryCiudadEstadio.setText("");
            entryCapacidadEstadio.setText("");
        }
    }

    private void actualizarComboBoxEquipos() {
        comboEquipoLocal.removeAllItems();
        comboEquipoVisitante.removeAllItems();
        for (Equipo equipo : mundial.getGrupos().get(0).getEquipos()) {
            comboEquipoLocal.addItem(equipo.getNombre());
            comboEquipoVisitante.addItem(equipo.getNombre());
        }
    }

    private Equipo buscarEquipo(String nombre) {
        for (Grupo grupo : mundial.getGrupos()) {
            for (Equipo equipo : grupo.getEquipos()) {
                if (equipo.getNombre().equals(nombre)) {
                    return equipo;
                }
            }
        }
        return null;
    }

    
        };
    


class Equipo {
    private String nombre;
    private String entrenador;
    private List<Jugador> jugadores;

    public Equipo(String nombre, String entrenador) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String mostrarInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Equipo: ").append(nombre).append("\n");
        info.append("Entrenador: ").append(entrenador).append("\n");
        info.append("Jugadores:\n");
        for (Jugador jugador : jugadores) {
            info.append(jugador.mostrarInfo()).append("\n");
        }
        return info.toString();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
}

class Jugador {
    private String nombre;
    private String edad;
    private String posicion;

    public Jugador(String nombre, String edad, String posicion) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
    }

    public String mostrarInfo() {
        return nombre + " - " + edad + " años - " + posicion;
    }
}

class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String resultado;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public void jugarPartido(String resultado) {
        this.resultado = resultado;
    }

    public String mostrarResultado() {
        return equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre() + " - Resultado: " + resultado;
    }
}

class Grupo {
    private String nombre;
    private List<Equipo> equipos;

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public String mostrarInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Grupo: ").append(nombre).append("\n");
        info.append("Equipos:\n");
        for (Equipo equipo : equipos) {
            info.append(equipo.mostrarInfo()).append("\n");
        }
        return info.toString();
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }
}

class Estadio {
    private String nombre;
    private String ciudad;
    private String capacidad;

    public Estadio(String nombre, String ciudad, String capacidad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
    }

    public String mostrarInfo() {
        return "Estadio: " + nombre + " - Ciudad: " + ciudad + " - Capacidad: " + capacidad;
    }
}

class Mundial {
    private List<Grupo> grupos;
    private List<Estadio> estadios;

    public Mundial() {
        this.grupos = new ArrayList<>();
        this.estadios = new ArrayList<>();
        // Inicializamos con un grupo por defecto
        grupos.add(new Grupo("Grupo A"));
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void registrarGrupo(Grupo grupo) {
        grupos.add(grupo);
    }

    public void registrarEstadio(Estadio estadio) {
        estadios.add(estadio);
    }

    public void generarFixture() {
        // Lógica para generar el fixture del mundial
    }
}
    

