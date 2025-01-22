# Contributing to OpenSearch Spring Boot Starter Project

We welcome contributions to this repository! This document outlines guidelines and instructions to help you contribute effectively.

---

## How to Contribute

1. **Fork the Repository**
   - Fork this repository to your own GitHub account.
   - Clone your forked repository locally.

   ```bash
   git clone https://github.com/noodlemind/opensearch-project.git
   cd opensearch-project
   ```

2. **Create a Branch**
   - Create a branch to work on your improvements or fixes.
   - Use a descriptive branch name like `feature/add-new-feature` or `fix/bug-description`.

   ```bash
   git checkout -b feature/your-feature
   ```

3. **Make Changes**
   - Follow the existing **code style** and **conventions** used in the project.
   - Make sure to write clear, concise commit messages.
   - Add tests for your code wherever applicable.
   - Update the documentation if necessary (e.g., README, code comments).

4. **Run Tests**
   - Ensure all tests pass locally before submitting your contributions.
   - If applicable, add new tests to verify your changes.

   ```bash
   ./mvnw clean test
   ```

5. **Submit a Pull Request**
   - Push your branch to your forked repository.
   - Submit a pull request to the main repository via GitHub.
   - Provide a detailed description of your changes and reference any related issues.

---

## Code Style Guidelines

- Use meaningful and descriptive variable and function names.
- Ensure functions serve a single purpose.
- Add comments where necessary to clarify complex sections of the code.
- Follow the indentation and formatting practices in the existing codebase.

---

## Reporting Issues

If you encounter a bug or have a feature request:

1. Search the [issue tracker](https://github.com/noodlemind/opensearch-project/issues) to see if it has already been reported.
2. If not, create a new issue with:
   - A descriptive title.
   - Steps to reproduce the problem or details about the desired feature.
   - Any relevant logs, screenshots, or context.

---

## Code of Conduct

Please note that this project adheres to a [Code of Conduct](./CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Be respectful to everyone and maintain a positive, inclusive work environment.

---

## Getting Help

If you need assistance, feel free to:
- Open a discussion in GitHub Discussions.
- Search for existing issues or discussions that may address your question.
- Reach out to the maintainers of the repository.

Thank you for contributing to this project!