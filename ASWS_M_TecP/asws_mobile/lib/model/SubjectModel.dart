
class Subject {
  final int id;
  final String name;
  final String description;

  Subject({
    required this.id,
    required this.name,
    required this.description,
  });

  factory Subject.fromJson(Map<String, dynamic> json) {
    return Subject(
      id: json['id'],
      name: json['name'],
      description: json['description'],
    );
  }
}
