# Decisiones humanas y correcciones

## Enum dominio

Se separó PaymentStatus de dominio y DTO generado.

## Repository

Se corrigió Optional.of(...) por Optional.ofNullable(...).

## Unit tests en General

Se ajustaron when(), verify(), asserts y casos negativos manualmente.

## Unit tests Controller Test

Se ajustaron test para que hicieran una covertura completa (Jacoco >=0.8)
más amplia de los métodos en Controller manualmente.

## Imports

Se reorganizaron imports de acuerdo a la convención para pasar pruebas de CheckSyles.

## Goals de Plugins

Se redefinieron goals de acuerdo a los objetivos del proyecto ejemplo: Jacoco, BugSpots y CheckStyles.

## Exlusión 

Se definió que código esta excluído de test automáticos.