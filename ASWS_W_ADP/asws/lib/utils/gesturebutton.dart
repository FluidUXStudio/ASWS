import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';

import 'dart:html' as html;

class HandCursor extends Listener {
  static final appContainer = html.window.document.getElementById('app-container');
  HandCursor({required Widget child}) : super(
      onPointerHover: (PointerHoverEvent evt) {
        appContainer?.style.cursor='pointer';
      },
      // onPointerDown: (PointerExitEvent evt) {
      //   appContainer.style.cursor='default';
      // },
      child: child
  );
}