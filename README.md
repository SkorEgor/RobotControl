
<img src="https://img.shields.io/badge/kotlin-%230095D5.svg?&style=for-the-badge&logo=kotlin&logoColor=white"/>![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)

<h3 align="center">RobotControl</h3>
<p align="center">
Android приложение для управления роботом по bluetooth
</p>

<div align="center">

![Application view](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/1.1.png)

</div>
<details>
  <summary>Содержание</summary>
  <ol>
    <li><a href="#О-проекте">О проекте</a></li>
    <li><a href="#Алгоритм-работы-с-приложением">Алгоритм работы с приложением</a></li>
    <li><a href="#Архитектура">Архитектура</a></li>
    <li><a href="#MenuActivity.kt-и-activity_menu.xml">MenuActivity.kt и activity_menu.xml</a></li>
    <li><a href="#ControlActivity.kt-и-activity_control.xml">ControlActivity.kt и activity_control.xml</a></li>
    <li><a href="#activity_main.xml-и-ConnectedBTActivity.kt">activity_main.xml и ConnectedBTActivity.kt</a></li>
    <li><a href="#list_item.xml-и-ListAdapterBT.kt">list_item.xml и ListAdapterBT.kt</a></li>
    <li><a href="#Connect">Connect</a></li>
    <li><a href="#Repeating-the-message">Repeating the message</a></li>
  </ol>
</details>


## О проекте
Android приложение для управления роботом по bluetooth, при помощи кнопок.
Каждая кнопка отсылает свой символ, при зажатом состоянии автоматически отправляются символы с задержкой в 20 мс (для этого в программе реализован отдельный поток).




## Алгоритм работы с приложением
1)	Зайти в настройки телефона, во вкладе bluetooth добавить новое устройство;
2)	Открыть приложение;
3)	Нажать кнопку «BLUETOTH CONTROL»;
4)	Установить связь с устройством
a.	В правом верхнем углу. Нажать на значок bluetooth
b.	В появившемся списке выбрать нужное устройство
5)	Начать управление, нажимая или зажимая кнопки


<div align="center">

![Demo](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/robot.gif)
</div>

## Архитектура
Данный пример имеет несколько Activity. 

### MenuActivity.kt и activity_menu.xml
Стартовое меню для выбора режима управления. При нажатии на кнопку переход к Activity с контроллером. 

<div align="center">

![3_MenuActivity.png](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/3_MenuActivity.png)
</div>

### ControlActivity.kt и activity_control.xml
Можно производить управление нажатием на кнопки или зажатием, а также в верхнем правом меню, кнопка для перехода к списку подключаемых устройств.

<div align="center">

![6_ControlActivity.png](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/6_ControlActivity.png)
</div>

### activity_main.xml и ConnectedBTActivity.kt
В выпадающем списке предлагается выбрать подключаемое устройство. Активность отвечает за инициализацию Bluetooth адаптера (проверка на поддержку Bluetooth устройством, предложение включить Bluetooth, сканирование), создание и отображение списка подключаемых устройств 

<div align="center">

![4_activity_main.png](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/4_activity_main.png)
</div>

### list_item.xml и ListAdapterBT.kt
Отвечает за вид и работу отдельного элемента списка Bluetooth (отображение имени и mac адреса устройства). Обработчик нажатия и установки данных.

<div align="center">

![5_list_item.png](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/5_list_item.png)
</div>


### Connect
Отвечает подключение к устройству.
BTConnect – принимает mac адрес для подключения, проверяет что адаптер поддерживается и передан не пустой параметр. Запрашиваем и сохраняем устройство  через adapter.getRemoteDevice. Запускам отдельный поток.
BTStreamThread – отслеживаем приходящие данные и отправляет сообщения


<div align="center">

![8_Connect.png](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/8_Connect.png)
</div>


### Repeating the message
При нажатии на кнопку создается поток. Для работы при зажатой кнопки отслеживаем события нажатия и отпускания конки. При нажатии запускается поток и передается символ сообщения, с этого момента поток, раз в 20 сек будет отправлять сообщение, до момента отпускания кнопки. 

<div align="center">

![7_Repeating the message](https://github.com/SkorEgor/picturesgifs-for-readme/blob/RobotControl/RobotControl/7_Repeating%20the%20message.png)
</div>

