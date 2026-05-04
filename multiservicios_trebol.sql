-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 04-05-2026 a las 23:25:25
-- Versión del servidor: 11.5.2-MariaDB
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `multiservicios_trebol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo_servicios_muelles`
--

DROP TABLE IF EXISTS `catalogo_servicios_muelles`;
CREATE TABLE IF NOT EXISTS `catalogo_servicios_muelles` (
  `id_servicio_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_servicio` varchar(100) NOT NULL,
  `descripcion_breve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_servicio_cat`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `catalogo_servicios_muelles`
--

INSERT INTO `catalogo_servicios_muelles` (`id_servicio_cat`, `nombre_servicio`, `descripcion_breve`) VALUES
(1, 'Ajuste de Suspensión', NULL),
(2, 'Cambio de Hojas', NULL),
(3, 'Refuerzo de Muelles', NULL),
(4, 'Mantenimiento General', NULL),
(5, 'Arqueo de Muelles', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre`, `telefono`, `correo`, `fecha_registro`) VALUES
(5, 'Hermelino', '784984949', 'dowinowin', '2026-05-04 05:25:58'),
(4, 'Levid', 'sexo', 'anal', '2026-05-04 05:21:04'),
(3, 'añoña', '75751110022', 'kmfefief@gmail.', '2026-05-03 23:27:13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_refaccion` varchar(100) NOT NULL,
  `cantidad_stock` int(11) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `alerta_minima` int(11) DEFAULT 5,
  PRIMARY KEY (`id_item`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_item`, `nombre_refaccion`, `cantidad_stock`, `precio_unitario`, `alerta_minima`) VALUES
(1, 'Abrazadera Muelle 5/8', 5, 150.00, 5),
(2, 'Abrazadera Muelle 5/8', 6, 150.00, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

DROP TABLE IF EXISTS `marcas`;
CREATE TABLE IF NOT EXISTS `marcas` (
  `id_marca` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_marca`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id_marca`, `nombre`) VALUES
(1, 'Nissan'),
(2, 'Ford'),
(3, 'Chevrolet');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

DROP TABLE IF EXISTS `modelos`;
CREATE TABLE IF NOT EXISTS `modelos` (
  `id_modelo` int(11) NOT NULL AUTO_INCREMENT,
  `id_marca` int(11) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_modelo`),
  KEY `fk_marca_modelo` (`id_marca`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `modelos`
--

INSERT INTO `modelos` (`id_modelo`, `id_marca`, `nombre`) VALUES
(1, 1, 'Versa'),
(2, 1, 'March'),
(3, 1, 'Sentra'),
(4, 2, 'Ranger'),
(5, 2, 'Explorer'),
(6, 2, 'Fiesta'),
(7, 3, 'Chevy'),
(8, 3, 'Silverado'),
(9, 3, 'Aveo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes_trabajo`
--

DROP TABLE IF EXISTS `ordenes_trabajo`;
CREATE TABLE IF NOT EXISTS `ordenes_trabajo` (
  `id_orden` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `id_vehiculo` int(11) DEFAULT NULL,
  `fecha_ingreso` timestamp NULL DEFAULT current_timestamp(),
  `fecha_entrega_estimada` datetime DEFAULT NULL,
  `estatus` enum('pendiente','en proceso','terminado','entregado') DEFAULT 'pendiente',
  `diagnostico_tecnico` text DEFAULT NULL,
  `descripcion_problema` text DEFAULT NULL,
  `costo_mano_obra` decimal(10,2) DEFAULT 0.00,
  `costo_refacciones` decimal(10,2) DEFAULT 0.00,
  PRIMARY KEY (`id_orden`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_vehiculo` (`id_vehiculo`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `ordenes_trabajo`
--

INSERT INTO `ordenes_trabajo` (`id_orden`, `id_cliente`, `id_vehiculo`, `fecha_ingreso`, `fecha_entrega_estimada`, `estatus`, `diagnostico_tecnico`, `descripcion_problema`, `costo_mano_obra`, `costo_refacciones`) VALUES
(1, 1, 1, '2026-05-03 07:44:05', NULL, 'en proceso', NULL, 'Revisión de muelles traseros por ruido excesivo', 1700.00, 300.00),
(2, 1, 1, '2026-05-04 03:53:14', NULL, 'en proceso', NULL, 'Revisión de muelles traseros por ruido excesivo', 1700.00, 300.00),
(3, 1, 1, '2026-05-04 03:53:23', NULL, 'en proceso', 'Actualizado desde sistema', 'Revisión de muelles traseros por ruido excesivo', 0.00, 1000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios_muelles`
--

DROP TABLE IF EXISTS `servicios_muelles`;
CREATE TABLE IF NOT EXISTS `servicios_muelles` (
  `id_muelle` int(11) NOT NULL AUTO_INCREMENT,
  `id_orden` int(11) DEFAULT NULL,
  `tipo_reparacion` varchar(100) DEFAULT NULL,
  `detalles_ajuste` text DEFAULT NULL,
  `piezas_especializadas` text DEFAULT NULL,
  `tecnico_responsable` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_muelle`),
  KEY `id_orden` (`id_orden`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `servicios_muelles`
--

INSERT INTO `servicios_muelles` (`id_muelle`, `id_orden`, `tipo_reparacion`, `detalles_ajuste`, `piezas_especializadas`, `tecnico_responsable`) VALUES
(1, 1, 'Ajuste de suspensión', '', 'Abrazadera Muelle 5/8', 'Levid'),
(2, 1, 'Ajuste de suspensión', '', 'Abrazadera Muelle 5/8', 'Levid'),
(3, 1, 'Ajuste de Suspensión', '', 'Abrazadera Muelle 5/8', 'Roberto Gómez'),
(4, 1, 'Ajuste de Suspensión', '', 'Abrazadera Muelle 5/8', 'Roberto Gómez'),
(5, 1, 'Arqueo de Muelles', '', 'Abrazadera Muelle 5/8', 'Roberto Gómez'),
(6, 1, 'Alineación', 'OBO', '1', 'OBO'),
(7, 1, 'Cambio de Hojas', 'ere', '1', 'guay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnicos`
--

DROP TABLE IF EXISTS `tecnicos`;
CREATE TABLE IF NOT EXISTS `tecnicos` (
  `id_tecnico` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `especialidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_tecnico`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `tecnicos`
--

INSERT INTO `tecnicos` (`id_tecnico`, `nombre`, `especialidad`) VALUES
(1, 'Roberto Gómez', 'Muelles'),
(2, 'Ricardo Luna', 'Suspensión'),
(3, 'Santi Trejo', 'General');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
CREATE TABLE IF NOT EXISTS `vehiculos` (
  `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `anio` int(11) DEFAULT NULL,
  `placas` varchar(15) NOT NULL,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `placas` (`placas`),
  KEY `id_cliente` (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id_vehiculo`, `id_cliente`, `marca`, `modelo`, `anio`, `placas`) VALUES
(1, 1, 'Ford', 'F-150', 2018, 'TRB-123'),
(3, 3, 'feewfwef', 'fwewfeewf', NULL, 'deedfew'),
(4, 3, 'Nissan', 'March', 2018, '777');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
