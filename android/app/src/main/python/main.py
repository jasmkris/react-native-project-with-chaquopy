from sqlite_todo import create_table, add_task, get_all_tasks, update_task, delete_task
from six.moves import input
# def run_task(parameter):
#     # Example function
#     return f"Hello, {parameter}!"

# Ensure table is created when Python starts
create_table()
# Function to interact with tasks
def run_task(task, action, task_id=None, done=None):
    if action == "add":
        add_task(task)
        return "Task added successfully."
    elif action == "get":
        return get_all_tasks()
    elif action == "update":
        update_task(task_id, done)
        return "Task updated successfully."
    elif action == "delete":
        delete_task(task_id)
        return "Task deleted successfully."
    else:
        return "Invalid action."

def main(key):
    print("Enter your name, or an empty line to exit.")
    while True:
        try:
            name = input()
        except EOFError:
            break
        if not name:
            break
        print("Hello {}!".format(name))