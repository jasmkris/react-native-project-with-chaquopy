import sqlite3
import os

# Path to SQLite database
DB_PATH = os.path.join(os.path.dirname(__file__), 'sqlite_todo.db')

# Create the to-do table if it doesn't exist
def create_table():
    connection = sqlite3.connect(DB_PATH)
    cursor = connection.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS todo (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            task TEXT NOT NULL,
            done INTEGER DEFAULT 0
        )
    ''')
    connection.commit()
    connection.close()

# Add a new task
def add_task(task):
    connection = sqlite3.connect(DB_PATH)
    cursor = connection.cursor()
    cursor.execute("INSERT INTO todo (task, done) VALUES (?, ?)", (task, 0))
    connection.commit()
    connection.close()

# Get all tasks
def get_all_tasks():
    connection = sqlite3.connect(DB_PATH)
    cursor = connection.cursor()
    cursor.execute("SELECT * FROM todo")
    rows = cursor.fetchall()
    connection.close()
    return rows

# Update a task (mark as done or undone)
def update_task(task_id, done):
    connection = sqlite3.connect(DB_PATH)
    cursor = connection.cursor()
    cursor.execute("UPDATE todo SET done = ? WHERE id = ?", (done, task_id))
    connection.commit()
    connection.close()

# Delete a task
def delete_task(task_id):
    connection = sqlite3.connect(DB_PATH)
    cursor = connection.cursor()
    cursor.execute("DELETE FROM todo WHERE id = ?", (task_id,))
    connection.commit()
    connection.close()
create_table()