-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-08-2018 a las 01:12:18
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cyberplay`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_cyberplay`
--

CREATE TABLE IF NOT EXISTS `cliente_cyberplay` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nacionalidad_cliente` varchar(4) NOT NULL,
  `cedula_cliente` varchar(100) NOT NULL,
  `nombre_cliente` varchar(100) NOT NULL,
  `apellido_cliente` varchar(60) NOT NULL,
  `codigotele_cliente` varchar(5) NOT NULL,
  `telefono_cliente` int(9) NOT NULL,
  `clientede_cliente` varchar(30) NOT NULL,
  `rutafoto_cliente` varchar(100) NOT NULL,
  `foto_cliente` varchar(50) NOT NULL,
  `fecharegistro_cliente` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `cedula_cliente` (`cedula_cliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `cliente_cyberplay`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentac_cliente_cyberplay`
--

CREATE TABLE IF NOT EXISTS `cuentac_cliente_cyberplay` (
  `id_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuentacliente` varchar(50) NOT NULL,
  `cedula_cliente` varchar(100) NOT NULL,
  `fondos_cuenta_bsf` varchar(50) NOT NULL,
  `fondos_cuenta_bss` varchar(50) NOT NULL,
  `fechacrea_cuenta` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `id_cuentacliente` (`id_cuentacliente`),
  UNIQUE KEY `cedula_cliente` (`cedula_cliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `cuentac_cliente_cyberplay`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expira_tarjeta`
--

CREATE TABLE IF NOT EXISTS `expira_tarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_expi` varchar(31) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `expira_tarjeta`
--

INSERT INTO `expira_tarjeta` (`id`, `fecha_expi`) VALUES
(1, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_cyberplay`
--

CREATE TABLE IF NOT EXISTS `inventario_cyberplay` (
  `id_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `idproduto_inventario` varchar(30) NOT NULL,
  `nombre_inventario` varchar(80) NOT NULL,
  `caracteristica_inventario` varchar(200) NOT NULL,
  `cantidad_inventario` int(11) NOT NULL,
  `preciounidad_inventario` int(11) NOT NULL,
  `moneda_inventario` varchar(30) NOT NULL,
  `rufoto_inventario` varchar(100) NOT NULL,
  `foto_inventario` varchar(50) NOT NULL,
  `fecha_inventario` varchar(30) NOT NULL,
  PRIMARY KEY (`id_inventario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento_cuenta_cyberplay`
--

CREATE TABLE IF NOT EXISTS `seguimiento_cuenta_cyberplay` (
  `id_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuentacliente` varchar(60) NOT NULL,
  `cedula_cliente` varchar(100) NOT NULL,
  `fondos_cuenta_bsf` varchar(80) NOT NULL,
  `fondos_cuenta_bss` varchar(50) NOT NULL,
  `recarga_retiro_cuenta_bsf` varchar(80) NOT NULL,
  `recarga_retiro_cuenta_bss` varchar(50) NOT NULL,
  `proceso_cuenta` varchar(80) NOT NULL,
  `numero_referencia` int(30) NOT NULL,
  `fecharecar_cuenta` varchar(30) NOT NULL,
  PRIMARY KEY (`id_cuenta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Volcado de datos para la tabla `seguimiento_cuenta_cyberplay`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento_inventario_cyverplay`
--

CREATE TABLE IF NOT EXISTS `seguimiento_inventario_cyverplay` (
  `id_seguimiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` varchar(30) NOT NULL,
  `nombre_producto` varchar(80) NOT NULL,
  `ventas_producto` int(11) NOT NULL,
  `total_producto` varchar(40) NOT NULL,
  `cedulacliente_producto` varchar(20) NOT NULL,
  `moneda_producto` varchar(12) NOT NULL,
  `fecha_producto` varchar(20) NOT NULL,
  PRIMARY KEY (`id_seguimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sistemab`
--

CREATE TABLE IF NOT EXISTS `sistemab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia` varchar(30) NOT NULL,
  `mes` varchar(30) NOT NULL,
  `ano` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `sistemab`
--

INSERT INTO `sistemab` (`id`, `dia`, `mes`, `ano`) VALUES
(1, '20', '8', '2019');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta_cliente`
--

CREATE TABLE IF NOT EXISTS `tarjeta_cliente` (
  `tarjetaid_tarjeta` int(11) NOT NULL AUTO_INCREMENT,
  `cedula_cliente` varchar(100) NOT NULL,
  `codigo_tarjeta` varchar(100) NOT NULL,
  `tipo_tarjeta` varchar(20) NOT NULL,
  `estado_tarjeta` varchar(20) NOT NULL,
  `clave_tarjeta` varchar(100) NOT NULL,
  `fechacrea_tarjeta` varchar(30) NOT NULL,
  `fechaven_tarjeta` varchar(30) NOT NULL,
  `diae` varchar(30) NOT NULL,
  `mese` varchar(30) NOT NULL,
  `anoe` varchar(30) NOT NULL,
  `rutafoto` varchar(100) NOT NULL,
  PRIMARY KEY (`tarjetaid_tarjeta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=77 ;

--
-- Volcado de datos para la tabla `tarjeta_cliente`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cedula_usuario` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `nombre_usuario` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `apellido_usuario` varchar(80) COLLATE latin1_general_ci NOT NULL,
  `sexo_usuario` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `usuario_usuario` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `contraseña_usuario` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `estado` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `rutafoto_usuario` varchar(150) COLLATE latin1_general_ci NOT NULL,
  `foto_usuario` varchar(50) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `usuario`
--



--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuentac_cliente_cyberplay`
--
ALTER TABLE `cuentac_cliente_cyberplay`
  ADD CONSTRAINT `Clien-Cuenta` FOREIGN KEY (`cedula_cliente`) REFERENCES `cliente_cyberplay` (`cedula_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
