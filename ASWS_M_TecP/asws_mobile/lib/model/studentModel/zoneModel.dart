class Zone {
  int id;
  String name;

  Zone({
    required this.id,
    required this.name,
  });

  factory Zone.fromJson(Map<String, dynamic> json) {
    return Zone(
      id: json['id'],
      name: json['name'],
    );
  }
}