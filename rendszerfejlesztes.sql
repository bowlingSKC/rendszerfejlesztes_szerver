-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2016. Már 21. 18:25
-- Kiszolgáló verziója: 5.6.24
-- PHP verzió: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Adatbázis: `rendszerfejlesztes`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `location_id` int(11) NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duration` int(11) NOT NULL,
  `performer_id` int(11) NOT NULL,
  `description` text COLLATE utf8_hungarian_ci,
  `price` int(11) NOT NULL,
  `seat` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `event`
--

INSERT INTO `event` (`id`, `name`, `location_id`, `start`, `duration`, `performer_id`, `description`, `price`, `seat`) VALUES
(3, 'Kool & the Gang', 5, '2016-06-16 18:00:00', 120, 3, NULL, 2500, 0),
(4, 'Emeli Sandé', 4, '2016-06-17 19:00:00', 120, 4, 'Veszprémben első magyarországi fellépésén köszönthetjük.', 3000, 0),
(5, 'Mike Stern & Didier Lockwood Band', 5, '2016-06-17 17:00:00', 200, 5, NULL, 1000, 0),
(6, 'Révész Richárd Latin Trió', 6, '2016-06-17 17:00:00', 60, 6, NULL, 500, 0),
(7, 'Ex - A. E. Bizottság a Palotában', 7, '2016-07-14 18:00:00', 500, 8, ' A Dubniczay-palota Várgalériája  ad helyet Ex - A. E. Bizottság a Palotában címmel egy rendhagyó kulturális programnak. Július 18-án Zalán Tibor író, költő, képzőművész megnyitójával és feLugossy László képzőművész, performer új filmjének vetítésével nyílik az Ex Vajda Lajos Stúdió kiállítása ef Zámbó Isván, feLugossy László és Wahorn András műveiből. A megnyitót követően a palota udvarában - az Albert Einstein BIZOTTSÁG egykori tagjainak közreműködésével - az efZámbó Happy Dead Band Akusztik és a Wahorn Airport adnak koncertet. Rossz Idő esetén a koncerteket a Hangvilla Multifunkcionális közösségi térben tartjuk. A program ingyenesen látogatható.', 0, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `location`
--

INSERT INTO `location` (`id`, `name`, `address`, `longitude`, `latitude`) VALUES
(4, 'Nagszínpad', '8200 Veszprém, Szentháromság tér', 47.0965347, 17.9031901),
(5, 'FestGarden', '8200 Veszprém, Remete köz 8', 47.0973616, 17.9038193),
(6, 'Rozé Rizling és Jazz Napok', '8200 Veszprém, Óváros tér', 47.0946001, 17.9061212),
(7, 'Dubniczay-palota', '8200 Veszprém, Vár u. 29', 47.0961115, 17.9034702),
(8, 'FestPresszó', '8200 Veszprém, Brusznyai Árpád u.', 47.0921978, 17.9090705);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `performer`
--

CREATE TABLE IF NOT EXISTS `performer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `description` text COLLATE utf8_hungarian_ci
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `performer`
--

INSERT INTO `performer` (`id`, `name`, `description`) VALUES
(3, 'Kool & the Gang', 'A Kool & the Gang világszerte több mint 70 millió albumot adott el, és három generáció zenei világára bírt jelentős hatással. Mindezek mellett - olyan számoknak köszönhetően, mint a Celebration, a Cherish, a Jungle Boogie, a Summer Madness és az Open Sesame - nyertek két Grammy-díjat, hét American Music Awardsot, jegyeztek huszonöt Top 10 listás R&B slágert, kilenc Top 10 pop slágert és harmincegy arany, illetve platina albumot. Az elmúlt több mint 40 éveben a Kool & the Gang folyamatosan játszott, hosszabb ideig, mint bármelyik együttes az R&B történetében. Bombabiztos funk és jazzy előadásmódjuk minden idők leginkább mintául szolgáló együttesévé avatta őket.'),
(4, 'Emeli Sandé', 'Vidéki skót környezetből származik. Tíz éves korától a zongora a hangszere és zeneszerzői eszköze. Hangjával pedig még korábban felhívta magára a figyelmet. A jobb keze alkarjára teljes hosszában a festő Frida Khalo-t tetováltatta – egy egyszerű, egyenes gondolkodású művésznőt.'),
(5, 'Mike Stern & Didier Lockwood Band', 'Mike Stern generációjának egyik legelismertebb elektromos gitárosa, 16 lemezéből hatot Grammy-díjra is jelöltek. Didier Lockwood hegedűművészt - 40 éves pályafutással a háta mögött - rendkívüli, egyedi hangzásáról ismerik a jazz világában, szerzőként és improvizációs előadóként egyaránt.'),
(6, 'Révész Richárd Latin Trió', 'Révész Richárd hazánk egyik első számú latin jazz zongoristája, eddig is nagy sikert aratott latin stílusú Escucha Mi ritmo, Menu 890 és Nuestro Ritmo című lemezeivel. A trió két másik tagja is méltán nagy népszerűségnek örvend. Fonay Tibor aki fiatal kora ellenére számos hazai formációban bizonyított már, nagyívű és dallamos szólóival kápráztatja el a közönséget és erősíti a triót. Az ütőhangszereknél Czibere József varázsol. Amit ő egyszemélyként tud, arra a világon is nagyon kevesen képesek. A koncertre látogató vendégek a saját kompozíciók mellett néhány népszerű dallam feldolgozását is meghallgathatják.'),
(8, 'Ex - A. E. Bizottság', NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `sector`
--

CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `num_of_rows` int(11) NOT NULL,
  `num_of_cols` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `depth` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `sector`
--

INSERT INTO `sector` (`id`, `event_id`, `num_of_rows`, `num_of_cols`, `price`, `depth`) VALUES
(2, 4, 2, 2, 200000, 1),
(3, 4, 10, 10, 2500, 2),
(4, 7, 10, 10, 5000, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL,
  `sector_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `booked_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `paid` tinyint(4) NOT NULL,
  `seat_row` int(11) DEFAULT NULL,
  `set_col` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `ticket`
--

INSERT INTO `ticket` (`id`, `sector_id`, `user_id`, `booked_time`, `paid`, `seat_row`, `set_col`, `status`) VALUES
(3, 2, 7, '2016-03-19 20:03:21', 0, NULL, NULL, 2),
(4, 3, 7, '2016-03-19 21:51:00', 0, NULL, NULL, 2),
(5, 2, 7, '2016-03-20 18:17:24', 0, NULL, NULL, 2),
(6, 2, 7, '2016-03-20 18:22:42', 0, NULL, NULL, 2),
(7, 4, 7, '2016-03-21 09:25:59', 0, NULL, NULL, 2),
(8, 4, 7, '2016-03-21 09:50:31', 0, NULL, NULL, 2),
(9, 4, 7, '2016-03-21 09:59:47', 0, NULL, NULL, 2),
(10, 4, 7, '2016-03-21 10:09:23', 0, NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `privilage` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `privilage`, `name`) VALUES
(7, 'airbusfan@t-online.hu', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 1, 'Lénárt Bálint'),
(8, 'admin@veszpremfest.hu', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 2, 'VeszpremFeszt Admin');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`), ADD KEY `location_id` (`location_id`), ADD KEY `performer_id` (`performer_id`);

--
-- A tábla indexei `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `performer`
--
ALTER TABLE `performer`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`,`depth`), ADD KEY `event_id` (`event_id`);

--
-- A tábla indexei `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`), ADD KEY `event_id` (`sector_id`), ADD KEY `user_id` (`user_id`), ADD KEY `sector_id` (`sector_id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT a táblához `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT a táblához `performer`
--
ALTER TABLE `performer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT a táblához `sector`
--
ALTER TABLE `sector`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT a táblához `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `event`
--
ALTER TABLE `event`
ADD CONSTRAINT `location_FK` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
ADD CONSTRAINT `performer_FK` FOREIGN KEY (`performer_id`) REFERENCES `performer` (`id`);

--
-- Megkötések a táblához `sector`
--
ALTER TABLE `sector`
ADD CONSTRAINT `FK_event_sector` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`);

--
-- Megkötések a táblához `ticket`
--
ALTER TABLE `ticket`
ADD CONSTRAINT `FK_event_sctor` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`),
ADD CONSTRAINT `FK_user_ticket` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
